package com.sea.pos.ui.algorithm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.sea.pos.algorithm.DataFormat
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.widget.*
import com.sea.pos.utils.I18nUtils

@Composable
fun CommonAlgoActivity() {
    val algos = listOf(
        I18nUtils.string(key = "common_algo_xor"),
        I18nUtils.string(key = "common_algo_xor_bitwise"),
        I18nUtils.string(key = "common_algo_base64"),
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

        RwHorizontalDivider()

        val selectedFormat = formats.indexOf(state.format)
        RwRadioGroup(list = formats.map { it.code }, label = "Data Format", selected = selectedFormat) { format ->
            val intent = DataFormat.valueOf(format).let { CommonAlgoIntent.SwitchFormat(it) }
            vm.dispatch(intent)
        }

        if (I18nUtils.string(key = "common_algo_xor") == state.algo) {
            XORScreen(vm, state)
        } else if (I18nUtils.string(key = "common_algo_base64") == state.algo) {
            RwSubtitleText("Input Data")
            Base64Screen(vm, state)
        } else {
            RwSubtitleText("Input Data")
            OtherScreen(vm, state)
        }

        RwVertical(height = Dimens.space_xxx)
    }

}

@Composable
private fun XORScreen(vm: CommonAlgoViewModel, state: CommonAlgoState) {
    RwSubtitleText("Component 1")

    RwInputTextWithLength(modifier = UiUtils.modifierOutput, value = state.component1) {
        val intent = CommonAlgoIntent.InputComponent1(component = it)
        vm.dispatch(intent)
    }

    RwSubtitleText("Component 2")

    RwInputTextWithLength(modifier = UiUtils.modifierOutput, value = state.component2) {
        val intent = CommonAlgoIntent.InputComponent2(component = it)
        vm.dispatch(intent)
    }

    RwSubtitleText("Output Data")

    RwInputTextWithLength(modifier = UiUtils.modifierOutput, value = state.outputData, input = false) { }

    RwTextCheckedButton(modifier = UiUtils.modifierSpace_xxx, text = "DONE") {
        vm.dispatch(intent = CommonAlgoIntent.Calculate)
    }
}

@Composable
private fun Base64Screen(vm: CommonAlgoViewModel, state: CommonAlgoState) {
    RwInputTextWithLength(modifier = UiUtils.modifierInput, value = state.inputData, maxLength = Int.MAX_VALUE) {
        val intent = CommonAlgoIntent.InputData(it)
        vm.dispatch(intent)
    }

    RwSubtitleText("Output Data")

    RwInputTextWithLength(modifier = UiUtils.modifierInput, value = state.outputData, input = false) { }

    Row(modifier = UiUtils.modifierSpace_xxx) {
        RwEncryptButton { vm.dispatch(intent = CommonAlgoIntent.Base64Encode) }

        RwHorizontal(width = Dimens.space_x)

        RwDecryptButton { vm.dispatch(intent = CommonAlgoIntent.Base64Decode) }
    }
}

@Composable
private fun OtherScreen(vm: CommonAlgoViewModel, state: CommonAlgoState) {
    RwInputTextWithLength(modifier = UiUtils.modifierInput, value = state.inputData) {
        val intent = CommonAlgoIntent.InputData(it)
        vm.dispatch(intent)
    }

    RwSubtitleText("Output Data")

    RwInputTextWithLength(modifier = UiUtils.modifierOutput, value = state.outputData, input = false) { }

    RwTextCheckedButton(modifier = UiUtils.modifierSpace_xxx, text = "DONE") {
        vm.dispatch(intent = CommonAlgoIntent.Calculate)
    }
}