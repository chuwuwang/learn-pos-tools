package com.sea.pos.ui.iso8583

import com.sea.pos.ui.BaseViewModel

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

    }

    private fun switchTag(intent: TagDecodeIntent.SwitchTag) {
        setState { copy(tag = intent.tag, outputData = "") }
    }

    private fun inputData(intent: TagDecodeIntent.InputData) {
        setState { copy(inputData = intent.data) }
    }

}