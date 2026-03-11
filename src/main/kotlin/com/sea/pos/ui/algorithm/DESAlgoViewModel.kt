package com.sea.pos.ui.algorithm

import com.sea.pos.algorithm.DataFormat
import com.sea.pos.algorithm.SymmetricEncryption
import com.sea.pos.algorithm.SymmetricMode
import com.sea.pos.algorithm.SymmetricPadding
import com.sea.pos.ui.BaseViewModel

class DESAlgoViewModel : BaseViewModel<DESAlgoState, Any>() {

    override fun initialState(): DESAlgoState {
        val state = DESAlgoState(
            format = DataFormat.Hex,
            mode = SymmetricMode.ECB,
            algo = SymmetricEncryption.DES,
            padding = SymmetricPadding.PKCS5Padding,
            iv = "0000000000000000",
        )
        return state
    }

    fun dispatch(intent: DESAlgoIntent) {
        when (intent) {
            DESAlgoIntent.Encrypt -> encrypt()
            DESAlgoIntent.Decrypt -> decrypt()
            is DESAlgoIntent.InputIV -> inputIV(intent)
            is DESAlgoIntent.InputKey -> inputKey(intent)
            is DESAlgoIntent.InputData -> inputData(intent)
            is DESAlgoIntent.SwitchAlgo -> switchAlgo(intent)
            is DESAlgoIntent.SwitchMode -> switchMode(intent)
            is DESAlgoIntent.SwitchFormat -> switchFormat(intent)
            is DESAlgoIntent.SwitchPadding -> switchPadding(intent)
        }
    }

    private fun encrypt() {
        val format = state.value.format
        state.value.key
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

sealed class DESAlgoIntent {

    class SwitchMode(val mode: SymmetricMode) : DESAlgoIntent()

    class SwitchFormat(val format: DataFormat) : DESAlgoIntent()

    class SwitchAlgo(val algo: SymmetricEncryption) : DESAlgoIntent()

    class SwitchPadding(val padding: SymmetricPadding) : DESAlgoIntent()

    class InputIV(val iv: String) : DESAlgoIntent()

    class InputKey(val key: String) : DESAlgoIntent()

    class InputData(val data: String) : DESAlgoIntent()

    object Encrypt : DESAlgoIntent()

    object Decrypt : DESAlgoIntent()

}

data class DESAlgoState(
    val format: DataFormat,
    val mode: SymmetricMode,
    val algo: SymmetricEncryption,
    val padding: SymmetricPadding,
    val iv: String = "",
    val key: String = "",
    val inputData: String = "",
    val outputData: String = "",
)