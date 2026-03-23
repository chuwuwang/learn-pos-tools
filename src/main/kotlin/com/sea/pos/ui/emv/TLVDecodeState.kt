package com.sea.pos.ui.emv

import com.sea.pos.tlv.TLV

data class TLVDecodeState(
    val inputData: String = "",
    val outputData: List<TLV> = emptyList(),
)