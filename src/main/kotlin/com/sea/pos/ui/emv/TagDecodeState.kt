package com.sea.pos.ui.emv

import com.sea.pos.emv.TagDecode

data class TagDecodeState(
    val tag: TagDecode = TagDecode.TVR,
    val inputData: String = "",
    val outputData: List< List<Boolean> > = emptyList(),
)