package com.sea.pos.utils

import java.util.*

object Base64Utils {

    fun base64ToBytes(base64: String): ByteArray ? {
        return Base64.getDecoder().decode(base64)
    }

    fun bytesToBase64(bytes: ByteArray): String ? {
        return Base64.getEncoder().encodeToString(bytes)
    }

}