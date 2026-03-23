package com.sea.pos.ui.emv

import com.sea.pos.ui.BaseViewModel

class TLVDecodeViewModel : BaseViewModel<TLVDecodeState, Any>() {

    override fun initialState(): TLVDecodeState {
        return TLVDecodeState()
    }

    fun dispatch(intent: TLVDecodeIntent) {
        when (intent) {
            TLVDecodeIntent.Decode -> decode()
            is TLVDecodeIntent.InputData -> inputData(intent)
        }
    }

    private fun decode() {

    }

    private fun inputData(intent: TLVDecodeIntent.InputData) {
        setState { copy(outputData = emptyList(), inputData = intent.data) }
    }

}