package com.sea.pos.ui.pmx

internal data class PMXResponse<T>(
    val data: T ? = null,
    val msg: String ? = null,
    val code: String ? = null,
)

internal data class ActivateInfo(
    val deviceId: String ? = null,
    val serverPublicKey: String ? = null,
)

internal typealias ActiveResponse = PMXResponse<ActivateInfo>