package com.sea.pos.ui.algorithm

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.sea.pos.algorithm.DataFormat
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.widget.*
import com.sea.pos.utils.I18nUtils

@Composable
fun CommonAlgoActivity() {
    val algos = listOf(
        I18nUtils.string("common_algo_xor"),
        I18nUtils.string("common_algo_xor_bitwise"),
        I18nUtils.string("common_algo_base64_encode"),
        I18nUtils.string("common_algo_base64_decode"),
    )
    val formats = listOf(DataFormat.Hex, DataFormat.Raw)

    val vm = remember { CommonAlgoViewModel() }
    val state by vm.state.collectAsState()

    Column {
        val selectedAlgo = algos.indexOf(state.algo)
        Topbar(list = algos, selected = selectedAlgo) {
            val intent = CommonAlgoIntent.SwitchAlgo(it)
            vm.dispatch(intent)
        }

        HorizontalDivider()

        val selectedFormat = formats.indexOf(state.format)
        RwRadioGroup(list = formats.map { it.code }, label = "Data Format", selected = selectedFormat) { format ->
            val intent = DataFormat.valueOf(format).let { CommonAlgoIntent.SwitchFormat(it) }
            vm.dispatch(intent)
        }

        if (I18nUtils.string("common_algo_xor") == state.algo) {
            RwSubtitleText("Component 1")

            RwInputTextWithLength(Modifier.weight(1f), state.component1, Int.MAX_VALUE) {
                val intent = CommonAlgoIntent.InputComponent1(it)
                vm.dispatch(intent)
            }

            RwSubtitleText("Component 2")

            RwInputTextWithLength(Modifier.weight(1f), state.component2, Int.MAX_VALUE) {
                val intent = CommonAlgoIntent.InputComponent2(it)
                vm.dispatch(intent)
            }
        } else {
            RwSubtitleText("Input Data")

            RwInputTextWithLength(Modifier.weight(3f), state.inputData, Int.MAX_VALUE) {
                val intent = CommonAlgoIntent.InputData(it)
                vm.dispatch(intent)
            }
        }

        RwSubtitleText("Output Data")

        RwInputTextWithLength(Modifier.weight(1f), state.outputData, Int.MAX_VALUE, input = false) { }

        RwTextCheckedButton(UiUtils.modifierSpace_xxx, "DONE") {
            vm.dispatch(CommonAlgoIntent.Calculate)
        }

        Vertical(Dimens.space_xxx)
    }

}