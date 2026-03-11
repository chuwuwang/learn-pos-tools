package com.sea.pos.ui.algorithm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.sea.pos.algorithm.DataFormat
import com.sea.pos.algorithm.SymmetricEncryption
import com.sea.pos.algorithm.SymmetricMode
import com.sea.pos.algorithm.SymmetricPadding
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.widget.*

@Composable
fun DESAlgoActivity() {
    val formats = listOf(DataFormat.Hex, DataFormat.Raw)
    val algos = listOf(SymmetricEncryption.DES, SymmetricEncryption.TripleDES)
    val paddings = listOf(
        SymmetricPadding.PKCS5Padding,
        SymmetricPadding.PKCS7Padding,
        SymmetricPadding.NoPadding,
        SymmetricPadding.ZeroPadding,
        SymmetricPadding.ANSIX923Padding,
        SymmetricPadding.ISO10126Padding,
    )
    val modes = listOf(SymmetricMode.ECB, SymmetricMode.CBC, SymmetricMode.CFB, SymmetricMode.OFB)

    val vm = remember { DESAlgoViewModel() }
    val state by vm.state.collectAsState()

    Column {
        val selectedAlgo = algos.indexOf(state.algo)
        Topbar(list = algos.map { it.code }, selected = selectedAlgo) { algo ->
            val item = SymmetricEncryption.entries.find { it.code == algo } ?: SymmetricEncryption.DES
            val intent = DESAlgoIntent.SwitchAlgo(item)
            vm.dispatch(intent)
        }

        HorizontalDivider()

        val selectedFormat = formats.indexOf(state.format)
        RwRadioGroup(list = formats.map { it.code }, label = "Data Format", selected = selectedFormat) { format ->
            val intent = DataFormat.valueOf(format).let { DESAlgoIntent.SwitchFormat(it) }
            vm.dispatch(intent)
        }

        val selectedMode = modes.indexOf(state.mode)
        RwRadioGroup(list = modes.map { it.code }, label = "Mode", selected = selectedMode) { mode ->
            val intent = SymmetricMode.valueOf(mode).let { DESAlgoIntent.SwitchMode(it) }
            vm.dispatch(intent)
        }

        val selectedPadding = modes.indexOf(state.mode)
        RwRadioGroup(list = paddings.map { it.code }, label = "Padding", selected = selectedPadding) { padding ->
            val intent = SymmetricPadding.valueOf(padding).let { DESAlgoIntent.SwitchPadding(it) }
            vm.dispatch(intent)
        }

        RwSubtitleText("Key")

        val maxLength = if (state.algo == SymmetricEncryption.DES) 16 else 32
        RwInputField(Modifier.height(Dimens.item_norm), state.key, maxLength, singleLine = true) {
            val intent = DESAlgoIntent.InputKey(it)
            vm.dispatch(intent)
        }

        RwSubtitleText("IV")

        RwInputField(Modifier.height(Dimens.item_norm), state.iv, maxLength, singleLine = true) {
            val intent = DESAlgoIntent.InputIV(it)
            vm.dispatch(intent)
        }

        RwSubtitleText("Input Data")

        RwInputField(Modifier.weight(2f), state.inputData, Int.MAX_VALUE) {
            val intent = DESAlgoIntent.InputData(it)
            vm.dispatch(intent)
        }

        RwSubtitleText("Output Data")

        RwInputField(Modifier.weight(1f), state.outputData, Int.MAX_VALUE, enabled = false) { }

        Row(UiUtils.modifierSpace_xxx) {
            RwEncryptButton { vm.dispatch(DESAlgoIntent.Encrypt) }

            Horizontal(Dimens.space_x)

            RwDecryptButton { vm.dispatch(DESAlgoIntent.Decrypt) }
        }

        Vertical(Dimens.space_xxx)
    }

}