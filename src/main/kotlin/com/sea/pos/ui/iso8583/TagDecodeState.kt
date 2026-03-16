package com.sea.pos.ui.iso8583

import com.sea.pos.emv.TagDecode

data class TagDecodeState(
    var tag: TagDecode = TagDecode.TVR,
    val inputData: String = "",
    val outputData: String = "",
)