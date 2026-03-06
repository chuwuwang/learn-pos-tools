package com.sea.pos.ui.algorithm

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.widget.*

@Composable
fun HashAlgoActivity(modifier: Modifier = Modifier) {
    val dataFormatList = listOf("Raw", "Hex")
    val algoList = listOf("MD2", "MD4", "MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512")

    val vm = remember { HashAlgoViewModel() }
    val state by vm.state.collectAsState()

    Column(modifier = modifier) {
        val algoSelected = algoList.indexOf(state.algo)
        Topbar(list = algoList, selected = algoSelected) {
            val intent = HashAlgoIntent.SwitchAlgo(it)
            vm.dispatch(intent)
        }

        HorizontalDivider()

        val dataFormatSelected = dataFormatList.indexOf(state.dataFormat)
        RwRadioGroup(list = listOf("Raw", "Hex"), label = "Data Format", selected = dataFormatSelected) {
            val intent = HashAlgoIntent.SwitchDataFormat(it)
            vm.dispatch(intent)
        }

        RwSubtitleText("Input Data")

        RwInputField(Modifier.weight(3f), state.inputData, Int.MAX_VALUE) {
            val intent = HashAlgoIntent.InputData(it)
            vm.dispatch(intent)
        }

        RwSubtitleText("Output Data")

        RwInputField(Modifier.weight(1f), state.outputData, Int.MAX_VALUE, enabled = false) {

        }

        RwDecryptButton(modifier = UiUtils.modifierSpace_xxx) {
            vm.dispatch(HashAlgoIntent.Encrypt)
        }

        Vertical(Dimens.space_xxx)
    }
}