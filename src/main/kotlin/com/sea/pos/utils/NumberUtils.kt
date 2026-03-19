package com.sea.pos.utils

object NumberUtils {

    fun booleans2HexString(bits: List<Boolean>): String {
        val paddedBits = if (bits.size % 8 == 0) {
            bits
        } else {
            bits + List(8 - bits.size % 8) { false } // 补 0
        }
        return paddedBits.chunked(8)
            .joinToString("") { byteBits ->
                var value = 0
                byteBits.forEachIndexed { index, bit ->
                    if (bit) {
                        value = value or (1 shl (7 - index)) // 高位在前
                    }
                }
                "%02X".format(value)
            }
    }

    fun formatLast6Bits(hex: String): String {
        val value = hex.toInt(16)    // 16进制转整数
        val last6Bits = value and 0x3F      // 去掉 b8 b7
        return last6Bits.toString(2).padStart(6, '0') // 转二进制并补齐6位
    }

}