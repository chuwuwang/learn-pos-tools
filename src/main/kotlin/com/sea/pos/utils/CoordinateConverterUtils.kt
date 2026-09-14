package com.sea.pos.utils

import kotlin.math.*

/**
 * 坐标系转换工具类
 *
 * 支持转换链路：
 *   BD-09（百度坐标系） -> GCJ-02（火星坐标系 / 国测局坐标系，高德、腾讯地图使用）
 *   BD-09（百度坐标系） -> WGS-84（国际标准坐标系 / GPS 原始坐标，Google Maps 国际版使用）
 *
 * 说明：
 * - GCJ-02 -> WGS-84 官方偏移算法本身不可逆，此处使用业界通用的
 *   "坐标偏移逆运算近似算法"，在国内城市区域精度可达 1~2 米级别，
 *   可满足地理围栏校验、地址匹配等大多数业务场景。
 * - 若对精度要求极高（厘米级），建议改用高德/百度官方坐标转换 API。
 */
object CoordinateConverterUtils {

    /** 圆周率相关常量，用于 BD-09 <-> GCJ-02 转换的加密算法 */
    private const val X_PI: Double = PI * 3000.0 / 180.0

    /** WGS-84 椭球长半轴 */
    private const val EARTH_RADIUS = 6378245.0

    /** WGS-84 椭球偏心率平方 */
    private const val EARTH_ECCENTRICITY_SQUARED: Double = 0.00669342162296594323

    /**
     * 经纬度坐标数据类
     *
     * @property lat 纬度
     * @property lon 经度
     */
    data class LatLng(val lat: Double, val lon: Double)

    /**
     * BD-09 坐标转换为 GCJ-02 坐标（火星坐标系）
     *
     * @param bdLat BD-09 纬度
     * @param bdLon BD-09 经度
     * @return 转换后的 GCJ-02 坐标
     */
    fun bd09ToGcj02(bdLat: Double, bdLon: Double): LatLng {
        val x = bdLon - 0.0065
        val y = bdLat - 0.006
        val z = sqrt(x * x + y * y) - 0.00002 * sin(y * X_PI)
        val theta = atan2(y, x) - 0.000003 * cos(x * X_PI)
        val gcjLon = z * cos(theta)
        val gcjLat = z * sin(theta)
        return LatLng(lat = gcjLat, lon = gcjLon)
    }

    /**
     * GCJ-02 坐标转换为 WGS-84 坐标（近似逆运算）
     *
     * @param gcjLat GCJ-02 纬度
     * @param gcjLon GCJ-02 经度
     * @return 转换后的 WGS-84 坐标
     */
    fun gcj02ToWgs84(gcjLat: Double, gcjLon: Double): LatLng {
        val dLat = transformLat(gcjLon - 105.0, gcjLat - 35.0)
        val dLon = transformLon(gcjLon - 105.0, gcjLat - 35.0)

        val radLat = gcjLat / 180.0 * PI
        var magic = sin(radLat)
        magic = 1 - EARTH_ECCENTRICITY_SQUARED * magic * magic
        val sqrtMagic = sqrt(magic)

        val finalDLat = (dLat * 180.0) / ((EARTH_RADIUS * (1 - EARTH_ECCENTRICITY_SQUARED)) / (magic * sqrtMagic) * PI)
        val finalDLon = (dLon * 180.0) / (EARTH_RADIUS / sqrtMagic * cos(radLat) * PI)

        val mgLat = gcjLat + finalDLat
        val mgLon = gcjLon + finalDLon

        return LatLng(lat = gcjLat * 2 - mgLat, lon = gcjLon * 2 - mgLon)
    }

    /**
     * BD-09 坐标直接转换为 WGS-84 坐标
     *
     * 转换链路：BD-09 -> GCJ-02 -> WGS-84
     *
     * @param bdLat BD-09 纬度
     * @param bdLon BD-09 经度
     * @return 转换后的 WGS-84 坐标
     */
    fun bd09ToWgs84(bdLat: Double, bdLon: Double): LatLng {
        val gcj02 = bd09ToGcj02(bdLat = bdLat, bdLon = bdLon)
        return gcj02ToWgs84(gcjLat = gcj02.lat, gcjLon = gcj02.lon)
    }

    private fun transformLat(x: Double, y: Double): Double {
        var ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * sqrt(abs(x))
        ret += (20.0 * sin(6.0 * x * PI) + 20.0 * sin(2.0 * x * PI)) * 2.0 / 3.0
        ret += (20.0 * sin(y * PI) + 40.0 * sin(y / 3.0 * PI)) * 2.0 / 3.0
        ret += (160.0 * sin(y / 12.0 * PI) + 320 * sin(y * PI / 30.0)) * 2.0 / 3.0
        return ret
    }

    private fun transformLon(x: Double, y: Double): Double {
        var ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * sqrt(abs(x))
        ret += (20.0 * sin(6.0 * x * PI) + 20.0 * sin(2.0 * x * PI)) * 2.0 / 3.0
        ret += (20.0 * sin(x * PI) + 40.0 * sin(x / 3.0 * PI)) * 2.0 / 3.0
        ret += (150.0 * sin(x / 12.0 * PI) + 300.0 * sin(x / 30.0 * PI)) * 2.0 / 3.0
        return ret
    }

}