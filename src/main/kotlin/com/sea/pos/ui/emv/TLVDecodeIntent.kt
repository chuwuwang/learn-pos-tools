package com.sea.pos.ui.emv

sealed class TLVDecodeIntent {

    object Decode : TLVDecodeIntent()

    class InputData(val data: String) : TLVDecodeIntent()

}