package com.sea.pos.ui.algorithm

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.sea.pos.algorithm.DataFormat
import com.sea.pos.algorithm.Hash
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.widget.*

@Composable
fun HashAlgoActivity(modifier: Modifier = Modifier) {
    val dataFormatList = listOf(DataFormat.Raw, DataFormat.Hex)
    val algoList = listOf(Hash.MD2, Hash.MD4, Hash.MD5, Hash.SHA1, Hash.SHA224, Hash.SHA256, Hash.SHA384, Hash.SHA512)

    val vm = remember { HashAlgoViewModel() }
    val state by vm.state.collectAsState()

    Column(modifier = modifier) {
        val selectedAlgo = algoList.indexOf(state.algo)
        Topbar(list = algoList.map { it.code }, selected = selectedAlgo) { algo ->
            val item = Hash.entries.find { it.code == algo } ?: Hash.MD2
            val intent = HashAlgoIntent.SwitchAlgo(item)
            vm.dispatch(intent)
        }

        HorizontalDivider()

        val dataFormatSelected = dataFormatList.indexOf(state.dataFormat)
        RwRadioGroup(list = dataFormatList.map { it.code }, label = "Data Format", selected = dataFormatSelected) {
            val item = DataFormat.valueOf(it)
            val intent = HashAlgoIntent.SwitchDataFormat(item)
            vm.dispatch(intent)
        }

        RwSubtitleText("Input Data")

        RwInputField(Modifier.weight(3f), state.inputData, Int.MAX_VALUE) {
            val intent = HashAlgoIntent.InputData(it)
            vm.dispatch(intent)
        }

        RwSubtitleText("Output Data")

        RwInputField(Modifier.weight(1f), state.outputData, Int.MAX_VALUE, enabled = false) { }

        RwDecryptButton(modifier = UiUtils.modifierSpace_xxx) {
            vm.dispatch(HashAlgoIntent.Encrypt)
        }

        Vertical(Dimens.space_xxx)
    }
}