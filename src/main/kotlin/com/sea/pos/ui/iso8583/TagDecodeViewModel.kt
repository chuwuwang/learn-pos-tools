package com.sea.pos.ui.iso8583

import com.pos.encode.util.ByteUtil
import com.sea.pos.emv.TagDecode
import com.sea.pos.ui.BaseViewModel
import kotlin.collections.chunked

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
            DialogHelper.showErrorMessageDialog("TVR must be 10 Hex Digits")
            return
        }
        if (tag == RiskControlTag.AIP && inputText.length != 4) {
            DialogHelper.showErrorMessageDialog("AIP must be 4 Hex Digits")
            return
        }
        if (tag == RiskControlTag.CVM && inputText.length != 6) {
            DialogHelper.showErrorMessageDialog("CVM must be 6 Hex Digits")
            return
        }
        if (tag == RiskControlTag.CTQ && inputText.length != 4) {
            DialogHelper.showErrorMessageDialog("CTQ must be 4 Hex Digits")
            return
        }
        val bytes = ByteUtil.hexString2Bytes(inputText)
        val booleans = ByteUtil.getBinaryFromByte(bytes).toList()
        outDataList.clear()
        outDataList.addAll(booleans)

        val bytes = ByteUtil.hexString2Bytes(state.i)
        val booleans = ByteUtil.bytes2BinaryBytes(bytes).toList().chunked(size = 8)

    }

    private fun switchTag(intent: TagDecodeIntent.SwitchTag) {
        setState { copy(tag = intent.tag, outputData = "") }
    }

    private fun inputData(intent: TagDecodeIntent.InputData) {
        setState { copy(inputData = intent.data) }
    }

}