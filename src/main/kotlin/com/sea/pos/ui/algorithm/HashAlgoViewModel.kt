package com.sea.pos.ui.algorithm

import com.pos.encode.algorithm.DataFormat
import com.pos.encode.algorithm.Hash
import com.pos.encode.algorithm.MD5Util
import com.pos.encode.algorithm.SHAUtil
import com.pos.encode.util.ByteUtil
import com.sea.pos.extension.empty
import com.sea.pos.extension.valid
import com.sea.pos.ui.BaseViewModel
import com.sea.pos.ui.widget.overlay.AppDialog
import com.sea.pos.ui.widget.overlay.DialogManager

class HashAlgoViewModel : BaseViewModel<HashAlgoState, Any>() {

    override fun initialState(): HashAlgoState {
        return HashAlgoState(algo = Hash.MD5, dataFormat = DataFormat.Raw)
    }

    fun dispatch(intent: HashAlgoIntent) {
        when (intent) {
            HashAlgoIntent.Encrypt -> encrypt()
            is HashAlgoIntent.InputData -> inputData(intent)
            is HashAlgoIntent.SwitchAlgo -> switchAlgo(intent)
            is HashAlgoIntent.SwitchDataFormat -> switchDataFormat(intent)
        }
    }

    private fun encrypt() {
        val algo = state.value.algo
        val inputData = state.value.inputData
        val dataFormat = state.value.dataFormat
        val b1 = inputData.empty
        val b2 = dataFormat == DataFormat.Hex && inputData.valid && inputData.length % 2 != 0
        if (b1 || b2) {
            val dialog = AppDialog.Error(message = "Data error")
            DialogManager.show(dialog)
            return
        }
        val bytes = if (dataFormat == DataFormat.Hex) {
            ByteUtil.hexString2Bytes(inputData)
        } else {
            inputData.toByteArray()
        }
        val outBytes = if (algo == Hash.MD4) {
            MD5Util.md4(bytes)
        } else if (algo == Hash.MD5) {
            MD5Util.md5(bytes)
        } else if (algo == Hash.SHA1) {
            SHAUtil.sha1(bytes)
        } else if (algo == Hash.SHA224) {
            SHAUtil.sha224(bytes)
        } else if (algo == Hash.SHA256) {
            SHAUtil.sha256(bytes)
        } else if (algo == Hash.SHA384) {
            SHAUtil.sha384(bytes)
        } else if (algo == Hash.SHA512) {
            SHAUtil.sha512(bytes)
        } else {
            MD5Util.md2(bytes)
        }
        if (outBytes != null) {
            val string = ByteUtil.bytes2HexString(outBytes)
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

    private fun switchDataFormat(intent: HashAlgoIntent.SwitchDataFormat) {
        setState { copy(dataFormat = intent.format) }
    }

}

sealed class HashAlgoIntent {

    class SwitchDataFormat(val format: DataFormat) : HashAlgoIntent()

    class SwitchAlgo(val algo: Hash) : HashAlgoIntent()

    class InputData(val data: String) : HashAlgoIntent()

    object Encrypt : HashAlgoIntent()

}


data class HashAlgoState(
    val algo: Hash,
    val dataFormat: DataFormat,
    val inputData: String = "",
    val outputData: String = "",
)