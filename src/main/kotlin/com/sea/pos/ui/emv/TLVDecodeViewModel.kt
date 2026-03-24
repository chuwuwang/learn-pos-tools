package com.sea.pos.ui.emv

import com.sea.pos.tlv.TLVUtils
import com.sea.pos.ui.BaseViewModel
import com.sea.pos.ui.widget.overlay.AppDialog
import com.sea.pos.ui.widget.overlay.DialogManager
import java.util.*

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
        try {
            val map = TLVUtils.toMap(state.value.inputData, true)
            val list = map.map { (_, value) -> value }
            setState { copy(outputData = list) }
        } catch (e: Throwable) {
            // ignore error and try to decode as base64
            decodeBase64()
        }
    }

    private fun decodeBase64() {
        try {
            val bytes = Base64.getDecoder().decode(state.value.inputData)
            val string = String(bytes)
            val map = TLVUtils.toMap(string, true)
            val list = map.map { (_, value) -> value }
            setState { copy(outputData = list) }
        } catch (e: Throwable) {
            e.printStackTrace()
            val dialog = AppDialog.Error(message = "Data error")
            DialogManager.show(dialog)
        }
    }

    private fun inputData(intent: TLVDecodeIntent.InputData) {
        setState { copy(outputData = emptyList(), inputData = intent.data) }
    }

}