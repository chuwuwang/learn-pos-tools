package com.sea.pos.ui.algorithm

import com.sea.pos.algorithm.DataFormat
import com.sea.pos.ui.BaseViewModel
import com.sea.pos.utils.I18nUtils

class CommonAlgoViewModel : BaseViewModel<CommonAlgoState, Any>() {

    override fun initialState(): CommonAlgoState {
        return CommonAlgoState(algo = I18nUtils.string("common_algo_xor"), format = DataFormat.Hex)
    }

    fun dispatch(intent: CommonAlgoIntent) {
        when (intent) {
            CommonAlgoIntent.Calculate -> TODO()
            is CommonAlgoIntent.InputComponent1 -> TODO()
            is CommonAlgoIntent.InputComponent2 -> TODO()
            is CommonAlgoIntent.InputData -> inputData(intent)
            is CommonAlgoIntent.SwitchAlgo -> switchAlgo(intent)
            is CommonAlgoIntent.SwitchFormat -> switchFormat(intent)
        }
    }

    private fun inputData(intent: CommonAlgoIntent.InputData) {
        setState { copy(inputData = intent.data) }
    }

    private fun switchAlgo(intent: CommonAlgoIntent.SwitchAlgo) {
        setState { copy(algo = intent.algo) }
    }

    private fun switchFormat(intent: CommonAlgoIntent.SwitchFormat) {
        setState { copy(format = intent.format) }
    }

}