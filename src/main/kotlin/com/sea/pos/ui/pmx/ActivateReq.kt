package com.sea.pos.ui.pmx

internal data class ActivateReq(
    var model: String = "",
    var vendor: String = "",
    var serialNumber: String = "",
    var encryptedPin: String = "",
    var clientPublicKey: String = "",
)