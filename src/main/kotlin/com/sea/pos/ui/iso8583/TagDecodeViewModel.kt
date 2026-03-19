package com.sea.pos.ui.iso8583

import com.pos.encode.util.ByteUtil
import com.sea.pos.emv.TagDecode
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
        if (tag == TagDecode.TVR && inputData.length != 10) {
            val dialog = AppDialog.Error(message = "TVR must be 10 Hex Digits")
            DialogManager.show(dialog)
        } else if (tag == TagDecode.AIP && inputData.length != 4) {
            val dialog = AppDialog.Error(message = "AIP must be 4 Hex Digits")
            DialogManager.show(dialog)
        } else if (tag == TagDecode.CVM && inputData.length != 6) {
            val dialog = AppDialog.Error(message = "CVM must be 6 Hex Digits")
            DialogManager.show(dialog)
        } else if (tag == TagDecode.CTQ && inputData.length != 4) {
            val dialog = AppDialog.Error(message = "CTQ must be 4 Hex Digits")
            DialogManager.show(dialog)
        } else {
            val bytes = ByteUtil.hexString2Bytes(inputData)
            val booleans = ByteUtil.bytes2BinaryBytes(bytes).toList().chunked(size = 8)
            setState { copy(outputData = booleans) }
        }
    }

    private fun switchTag(intent: TagDecodeIntent.SwitchTag) {
        setState { copy(inputData = "", outputData = emptyList(), tag = intent.tag) }
    }

    private fun inputData(intent: TagDecodeIntent.InputData) {
        setState { copy(inputData = intent.data) }
    }

}