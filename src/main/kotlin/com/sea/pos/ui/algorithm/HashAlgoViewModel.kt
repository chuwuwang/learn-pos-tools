package com.sea.pos.ui.algorithm

import com.sea.pos.ui.BaseViewModel

class HashAlgoViewModel : BaseViewModel<HashAlgoState, Any>() {

    override fun initialState(): HashAlgoState {
        return HashAlgoState(algo = "MD5", dataFormat = "Raw")
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

    class SwitchDataFormat(val format: String) : HashAlgoIntent()

    class SwitchAlgo(val algo: String) : HashAlgoIntent()

    class InputData(val data: String) : HashAlgoIntent()

    object Encrypt : HashAlgoIntent()

}


data class HashAlgoState(
    val inputData: String = "",
    val outputData: String = "",
    val algo: String = "",
    val dataFormat: String = "",
)