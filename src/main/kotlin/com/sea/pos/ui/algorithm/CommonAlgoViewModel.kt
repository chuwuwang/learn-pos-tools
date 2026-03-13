package com.sea.pos.ui.algorithm

import com.pos.encode.algorithm.AlgorithmUtil
import com.pos.encode.util.ByteUtil
import com.sea.pos.algorithm.DataFormat
import com.sea.pos.extension.isInvalidInput
import com.sea.pos.ui.BaseViewModel
import com.sea.pos.ui.widget.overlay.AppDialog
import com.sea.pos.ui.widget.overlay.DialogManager
import com.sea.pos.utils.I18nUtils
import java.util.*

class CommonAlgoViewModel : BaseViewModel<CommonAlgoState, Any>() {

    override fun initialState(): CommonAlgoState {
        return CommonAlgoState(algo = I18nUtils.string("common_algo_xor"), format = DataFormat.Hex)
    }

    fun dispatch(intent: CommonAlgoIntent) {
        when (intent) {
            CommonAlgoIntent.Calculate -> calculate()
            CommonAlgoIntent.Base64Encode -> base64(true)
            CommonAlgoIntent.Base64Decode -> base64(false)
            is CommonAlgoIntent.InputComponent1 -> inputComponent1(intent)
            is CommonAlgoIntent.InputComponent2 -> inputComponent2(intent)
            is CommonAlgoIntent.InputData -> inputData(intent)
            is CommonAlgoIntent.SwitchAlgo -> switchAlgo(intent)
            is CommonAlgoIntent.SwitchFormat -> switchFormat(intent)
        }
    }

    private fun calculate() {
        val algo = state.value.algo
        val inputData = state.value.inputData
        val invalid = inputData.isInvalidInput(state.value.format)
        if (invalid) {
            val dialog = AppDialog.Error(message = "Data error")
            DialogManager.show(dialog)
            return
        }
        if (I18nUtils.string("common_algo_xor") == algo) {
            xor()
        } else if (I18nUtils.string("common_algo_xor_bitwise") == algo) {
            xorBitwise(inputData)
        }
    }

    private fun xor() {
        val component1 = state.value.component1
        val component2 = state.value.component2
        val b1 = component1.isInvalidInput(state.value.format)
        val b2 = component2.isInvalidInput(state.value.format)
        if (b1 || b2 || component1.length != component2.length) {
            val dialog = AppDialog.Error(message = "Data error")
            DialogManager.show(dialog)
            return
        }
        var dataInComponent1: ByteArray
        var dataInComponent2: ByteArray
        if (state.value.format == DataFormat.Raw) {
            dataInComponent1 = component1.toByteArray()
            dataInComponent2 = component2.toByteArray()
        } else {
            dataInComponent1 = ByteUtil.hexString2Bytes(component1)
            dataInComponent2 = ByteUtil.hexString2Bytes(component2)
        }
        val dataOut = AlgorithmUtil.xor(dataInComponent1, dataInComponent2)
        if (dataOut != null) {
            val string = ByteUtil.bytes2HexString(dataOut)
            setState { copy(outputData = string) }
        } else {
            val dialog = AppDialog.Error(message = "Calculation failed")
            DialogManager.show(dialog)
        }
    }

    private fun xorBitwise(data: String) {
        val dataIn = if (state.value.format == DataFormat.Raw) {
            data.toByteArray()
        } else {
            ByteUtil.hexString2Bytes(data)
        }
        val dataOut = AlgorithmUtil.xorBitwise(dataIn)
        val string = ByteUtil.byte2HexString(dataOut).uppercase()
        setState { copy(outputData = string) }
    }

    private fun base64(encode: Boolean) {
        val inputData = state.value.inputData
        val invalid = inputData.isInvalidInput(state.value.format)
        if (invalid) {
            val dialog = AppDialog.Error(message = "Data error")
            DialogManager.show(dialog)
            return
        }
        val dataIn = if (state.value.format == DataFormat.Raw) {
            inputData.toByteArray()
        } else {
            ByteUtil.hexString2Bytes(inputData)
        }
        val dataOut = if (encode) {
            Base64.getEncoder().withoutPadding().encode(dataIn)
        } else {
            Base64.getDecoder().decode(dataIn)
        }
        val string = ByteUtil.bytes2HexString(dataOut)
        setState { copy(outputData = string) }
    }

    private fun inputComponent1(intent: CommonAlgoIntent.InputComponent1) {
        setState { copy(component1 = intent.component) }
    }

    private fun inputComponent2(intent: CommonAlgoIntent.InputComponent2) {
        setState { copy(component2 = intent.component) }
    }

    private fun inputData(intent: CommonAlgoIntent.InputData) {
        setState { copy(inputData = intent.data) }
    }

    private fun switchAlgo(intent: CommonAlgoIntent.SwitchAlgo) {
        setState { copy(algo = intent.algo, outputData = "") }
    }

    private fun switchFormat(intent: CommonAlgoIntent.SwitchFormat) {
        setState { copy(format = intent.format) }
    }

}