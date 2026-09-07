package com.sea.pos.ui.pmx

internal data class PMXState(
    val feature: PMXFeature,
    val requestUrl: String = "",
    val activateReq: ActivateReq,

    val publicKey: String = "",
    val privateKey: String = "",
)