package com.sea.pos.ui.algorithm

import com.pos.encode.algorithm.MD5Util
import com.pos.encode.algorithm.SHAUtil
import com.pos.encode.util.ByteUtil
import com.sea.pos.algorithm.DataFormat
import com.sea.pos.algorithm.Hash
import com.sea.pos.extension.isInputInvalid
import com.sea.pos.ui.BaseViewModel
import com.sea.pos.ui.widget.overlay.AppDialog
import com.sea.pos.ui.widget.overlay.DialogManager

class HashAlgoViewModel : BaseViewModel<HashAlgoState, Any>() {

    override fun initialState(): HashAlgoState {
        return HashAlgoState(algo = Hash.MD2, format = DataFormat.Hex)
    }

    fun dispatch(intent: HashAlgoIntent) {
        when (intent) {
            HashAlgoIntent.Encrypt -> encrypt()
            is HashAlgoIntent.InputData -> inputData(intent)
            is HashAlgoIntent.SwitchAlgo -> switchAlgo(intent)
            is HashAlgoIntent.SwitchFormat -> switchFormat(intent)
        }
    }

    private fun encrypt() {
        val algo = state.value.algo
        val format = state.value.format
        val inputData = state.value.inputData
        val invalid = inputData.isInputInvalid(format)
        if (invalid) {
            val dialog = AppDialog.Error(message = "Data error")
            DialogManager.show(dialog)
            return
        }
        val dataIn = if (format == DataFormat.Hex) {
            ByteUtil.hexString2Bytes(inputData)
        } else {
            inputData.toByteArray()
        }
        val dataOut = if (algo == Hash.MD4) {
            MD5Util.md4(dataIn)
        } else if (algo == Hash.MD5) {
            MD5Util.md5(dataIn)
        } else if (algo == Hash.SHA1) {
            SHAUtil.sha1(dataIn)
        } else if (algo == Hash.SHA224) {
            SHAUtil.sha224(dataIn)
        } else if (algo == Hash.SHA256) {
            SHAUtil.sha256(dataIn)
        } else if (algo == Hash.SHA384) {
            SHAUtil.sha384(dataIn)
        } else if (algo == Hash.SHA512) {
            SHAUtil.sha512(dataIn)
        } else {
            MD5Util.md2(dataIn)
        }
        if (dataOut != null) {
            val string = ByteUtil.bytes2HexString(dataOut)
            setState { copy(outputData = string) }
        } else {
            val dialog = AppDialog.Error(message = "Encryption failed")
            DialogManager.show(dialog)
        }
    }

    private fun inputData(intent: HashAlgoIntent.InputData) {
        setState { copy(inputData = intent.data) }
    }

    private fun switchAlgo(intent: HashAlgoIntent.SwitchAlgo) {
        setState { copy(algo = intent.algo) }
    }

    private fun switchFormat(intent: HashAlgoIntent.SwitchFormat) {
        setState { copy(format = intent.format) }
    }

}