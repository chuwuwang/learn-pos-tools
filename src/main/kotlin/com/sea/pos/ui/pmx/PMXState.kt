package com.sea.pos.ui.pmx

internal data class PMXState(
    val feature: PMXFeature,
    val requestUrl: String = "",
    val activateReq: ActivateReq = ActivateReq(),
    val publicKey: String = "",
    val privateKey: String = "",
    val activateInfo: ActivateInfo = ActivateInfo(),

    val coordinateType: CoordinateType = CoordinateType.BD09,
    val inputLat: String = "",
    val inputLon: String = "",
    val outputGcj02: String = "",
    val outputWgs84: String = "",
)