package com.sea.pos.ui.algorithm

import com.pos.encode.algorithm.DESUtil
import com.pos.encode.algorithm.TripleDESUtil
import com.pos.encode.utils.ByteUtils
import com.sea.pos.algorithm.DataFormat
import com.sea.pos.algorithm.SymmetricEncryption
import com.sea.pos.algorithm.SymmetricMode
import com.sea.pos.algorithm.SymmetricPadding
import com.sea.pos.extension.isInvalidInput
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
            key = "",
            inputData = "",
        )
        return state
    }

    fun dispatch(intent: DESAlgoIntent) {
        when (intent) {
            DESAlgoIntent.Encrypt -> calculate(true)
            DESAlgoIntent.Decrypt -> calculate(false)
            is DESAlgoIntent.InputIV -> inputIV(intent)
            is DESAlgoIntent.InputKey -> inputKey(intent)
            is DESAlgoIntent.InputData -> inputData(intent)
            is DESAlgoIntent.SwitchAlgo -> switchAlgo(intent)
            is DESAlgoIntent.SwitchMode -> switchMode(intent)
            is DESAlgoIntent.SwitchFormat -> switchFormat(intent)
            is DESAlgoIntent.SwitchPadding -> switchPadding(intent)
        }
    }

    private fun calculate(encrypt: Boolean) {
        val iv = state.value.iv
        val key = state.value.key
        val mode = state.value.mode
        val format = state.value.format
        val inputData = state.value.inputData
        val triple = state.value.algo == SymmetricEncryption.TripleDES
        val size = if (triple) 16 else 8
        if (key.length != size && key.length != size * 2) {
            val dialog = AppDialog.Error(message = "key size must be $size bytes")
            DialogManager.show(dialog)
            return
        }
        if (mode != SymmetricMode.ECB && iv.length != 8 && iv.length != 16) {
            val dialog = AppDialog.Error(message = "IV size must be 8 bytes")
            DialogManager.show(dialog)
            return
        }
        val invalid = inputData.isInvalidInput(format)
        if (invalid) {
            val dialog = AppDialog.Error(message = "Data error")
            DialogManager.show(dialog)
            return
        }
        var ivBytes = if (iv.length == 8) {
            iv.toByteArray()
        } else {
            ByteUtils.hexString2Bytes(iv)
        }
        if (mode == SymmetricMode.ECB) {
            ivBytes = null
        }
        val keyBytes = if (key.length == size) {
            key.toByteArray()
        } else {
            ByteUtils.hexString2Bytes(key)
        }
        val dataIn = if (format == DataFormat.Raw) {
            inputData.toByteArray()
        } else {
            ByteUtils.hexString2Bytes(inputData)
        }
        if (encrypt) {
            encrypt(triple, keyBytes, dataIn, ivBytes)
        } else {
            decrypt(triple, keyBytes, dataIn, ivBytes)
        }
    }

    private fun encrypt(triple: Boolean, keyBytes: ByteArray, dataIn: ByteArray, ivBytes: ByteArray ? ) {
        val dataOut = if (triple) {
            TripleDESUtil.encrypt(keyBytes, dataIn, ivBytes, state.value.mode.code, state.value.padding.name)
        } else {
            DESUtil.encrypt(keyBytes, dataIn, ivBytes, state.value.mode.code, state.value.padding.name)
        }
        if (dataOut != null) {
            val string = ByteUtils.bytes2HexString(dataOut)
            setState { copy(outputData = string) }
        } else {
            val dialog = AppDialog.Error(message = "Encryption failed")
            DialogManager.show(dialog)
        }
    }

    private fun decrypt(triple: Boolean, keyBytes: ByteArray, dataIn: ByteArray, ivBytes: ByteArray ? ) {
        val dataOut = if (triple) {
            TripleDESUtil.decrypt(keyBytes, dataIn, ivBytes, state.value.mode.code, state.value.padding.name)
        } else {
            DESUtil.decrypt(keyBytes, dataIn, ivBytes, state.value.mode.code, state.value.padding.name)
        }
        if (dataOut != null) {
            val string = ByteUtils.bytes2HexString(dataOut)
            setState { copy(outputData = string) }
        } else {
            val dialog = AppDialog.Error(message = "Decryption failed")
            DialogManager.show(dialog)
        }
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