package com.sea.pos.ui.algorithm

import com.pos.encode.algorithm.DESUtil
import com.pos.encode.util.ByteUtil
import com.sea.pos.algorithm.DataFormat
import com.sea.pos.algorithm.SymmetricEncryption
import com.sea.pos.algorithm.SymmetricMode
import com.sea.pos.algorithm.SymmetricPadding
import com.sea.pos.extension.isInputInvalid
import com.sea.pos.ui.BaseViewModel
import com.sea.pos.ui.widget.overlay.AppDialog
import com.sea.pos.ui.widget.overlay.DialogManager

class DESAlgoViewModel : BaseViewModel<DESAlgoState, Any>() {

    override fun initialState(): DESAlgoState {
        val state = DESAlgoState(
            format = DataFormat.Hex,
            mode = SymmetricMode.ECB,
            algo = SymmetricEncryption.DES,
            padding = SymmetricPadding.NoPadding,
            iv = "0000000000000000",
            key = "1111111111111111",
            inputData = "0000000000000000",
        )
        return state
    }

    fun dispatch(intent: DESAlgoIntent) {
        when (intent) {
            DESAlgoIntent.Encrypt -> {
                if (state.value.algo == SymmetricEncryption.DES) {
                    encryptByDES()
                } else {

                }
            }

            DESAlgoIntent.Decrypt -> {

            }

            is DESAlgoIntent.InputIV -> inputIV(intent)
            is DESAlgoIntent.InputKey -> inputKey(intent)
            is DESAlgoIntent.InputData -> inputData(intent)
            is DESAlgoIntent.SwitchAlgo -> switchAlgo(intent)
            is DESAlgoIntent.SwitchMode -> switchMode(intent)
            is DESAlgoIntent.SwitchFormat -> switchFormat(intent)
            is DESAlgoIntent.SwitchPadding -> switchPadding(intent)
        }
    }

    private fun encryptByDES() {
        val iv = state.value.iv
        val key = state.value.key
        val mode = state.value.mode
        val format = state.value.format
        val inputData = state.value.inputData
        if (key.length != 8 && key.length != 16) {
            val dialog = AppDialog.Error(message = "key size must be 8 bytes")
            DialogManager.show(dialog)
            return
        }
        val invalid = inputData.isInputInvalid(format)
        if (invalid) {
            val dialog = AppDialog.Error(message = "Data error")
            DialogManager.show(dialog)
            return
        }
        val keyBytes = if (key.length == 8) {
            key.toByteArray()
        } else {
            ByteUtil.hexString2Bytes(key)
        }
        val dataIn = if (state.value.format == DataFormat.Raw) {
            state.value.inputData.toByteArray()
        } else {
            ByteUtil.hexString2Bytes(state.value.inputData)
        }
        val dataOut = if (mode == SymmetricMode.ECB) {
            DESUtil.encryptECB(keyBytes, dataIn, state.value.padding.code)
        } else {
            byteArrayOf()
        }
        if (dataOut != null) {
            val string = ByteUtil.bytes2HexString(dataOut)
            setState { copy(outputData = string) }
        } else {
            val dialog = AppDialog.Error(message = "Encryption failed")
            DialogManager.show(dialog)
        }
    }

    private fun decrypt() {

    }

    private fun inputIV(intent: DESAlgoIntent.InputIV) {
        setState { copy(iv = intent.iv) }
    }

    private fun inputKey(intent: DESAlgoIntent.InputKey) {
        setState { copy(key = intent.key) }
    }

    private fun inputData(intent: DESAlgoIntent.InputData) {
        setState { copy(inputData = intent.data) }
    }

    private fun switchAlgo(intent: DESAlgoIntent.SwitchAlgo) {
        setState { copy(algo = intent.algo) }
    }

    private fun switchFormat(intent: DESAlgoIntent.SwitchFormat) {
        setState { copy(format = intent.format) }
    }

    private fun switchMode(intent: DESAlgoIntent.SwitchMode) {
        setState { copy(mode = intent.mode) }
    }

    private fun switchPadding(intent: DESAlgoIntent.SwitchPadding) {
        setState { copy(padding = intent.padding) }
    }

}