package com.sea.pos.ui.emv

import com.pos.encode.utils.ByteUtils
import com.sea.pos.ui.BaseViewModel
import com.sea.pos.ui.widget.overlay.AppDialog
import com.sea.pos.ui.widget.overlay.DialogManager

class TagDecodeViewModel : BaseViewModel<TagDecodeState, Any>() {

    override fun initialState(): TagDecodeState {
        return TagDecodeState()
    }

    fun dispatch(intent: TagDecodeIntent) {
        when (intent) {
            TagDecodeIntent.Decode -> calculate()
            is TagDecodeIntent.SwitchTag -> switchTag(intent)
            is TagDecodeIntent.InputData -> inputData(intent)
        }
    }

    private fun calculate() {
        val tag = state.value.tag
        val inputData = state.value.inputData
        if (inputData.length != tag.length) {
            val length = tag.length
            val dialog = AppDialog.Error(message = tag.name + " must be $length Hex Digits")
            DialogManager.show(dialog)
        } else {
            val bytes = ByteUtils.hexString2Bytes(inputData)
            val booleans = ByteUtils.bytes2BinaryBytes(bytes).toList().chunked(size = 8)
            setState { copy(outputData = booleans) }
        }
    }

    private fun switchTag(intent: TagDecodeIntent.SwitchTag) {
        setState { copy(outputData = emptyList(), inputData = "", tag = intent.tag) }
    }

    private fun inputData(intent: TagDecodeIntent.InputData) {
        setState { copy(outputData = emptyList(), inputData = intent.data) }
    }

}