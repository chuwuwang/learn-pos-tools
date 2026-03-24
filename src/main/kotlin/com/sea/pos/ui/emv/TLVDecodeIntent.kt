package com.sea.pos.ui.emv

sealed class TLVDecodeIntent {

    object Decode : TLVDecodeIntent()

    object Search : TLVDecodeIntent()

    class InputData(val data: String) : TLVDecodeIntent()

}