package com.sea.pos.tlv

data class TLV(var tag: String = "", var length: Int = 0, var value: String = "") {

    fun toHexString(): String {
        return TLVUtils.toHexString(this)
    }

    fun toByteArray(): ByteArray {
        return TLVUtils.toByteArray(this)
    }

}