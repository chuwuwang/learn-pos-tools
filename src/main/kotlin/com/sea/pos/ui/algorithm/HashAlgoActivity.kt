package com.sea.pos.ui.algorithm

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.sea.pos.algorithm.DataFormat
import com.sea.pos.algorithm.Hash
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.widget.*

@Composable
fun HashAlgoActivity() {
    val formats = listOf(DataFormat.Hex, DataFormat.Raw)
    val algos = listOf(Hash.MD2, Hash.MD4, Hash.MD5, Hash.SHA1, Hash.SHA224, Hash.SHA256, Hash.SHA384, Hash.SHA512)

    val vm = remember { HashAlgoViewModel() }
    val state by vm.state.collectAsState()

    Column {
        val selectedAlgo = algos.indexOf(state.algo)
        Topbar(list = algos.map { it.code }, selected = selectedAlgo) { algo ->
            val item = Hash.entries.find { it.code == algo } ?: Hash.MD5
            val intent = HashAlgoIntent.SwitchAlgo(item)
            vm.dispatch(intent)
        }

        HorizontalDivider()

        val selectedFormat = formats.indexOf(state.format)
        RwRadioGroup(list = formats.map { it.code }, label = "Data Format", selected = selectedFormat) { format ->
            val intent = DataFormat.valueOf(format).let { HashAlgoIntent.SwitchFormat(it) }
            vm.dispatch(intent)
        }

        RwSubtitleText("Input Data")

        RwInputTextWithLength(modifier = UiUtils.modifierInput, value = state.inputData) {
            val intent = HashAlgoIntent.InputData(it)
            vm.dispatch(intent)
        }

        RwSubtitleText("Output Data")

        RwInputTextWithLength(modifier = UiUtils.modifierOutput, value = state.outputData, input = false) { }

        RwEncryptButton(modifier = UiUtils.modifierSpace_xxx) {
            vm.dispatch(intent = HashAlgoIntent.Encrypt)
        }

        Vertical(height = Dimens.space_xxx)
    }

}