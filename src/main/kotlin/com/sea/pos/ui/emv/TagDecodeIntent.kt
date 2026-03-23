package com.sea.pos.ui.emv

import com.sea.pos.emv.TagDecode

sealed class TagDecodeIntent {

    object Decode : TagDecodeIntent()

    class SwitchTag(val tag: TagDecode) : TagDecodeIntent()

    class InputData(val data: String) : TagDecodeIntent()

}