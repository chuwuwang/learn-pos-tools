package com.sea.pos.ui.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.sea.pos.algorithm.DataFormat
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.widget.*

@Composable
fun ImageTransformActivity() {
    val features = listOf("QRCode Generate", "Base64 to Image", "Image to Base64")

    val vm = remember { ImageTransformViewModel() }
    val state by vm.state.collectAsState()

    Column {
        val selected = features.indexOf(state.feature)
        Topbar(list = features, selected = selected) {
            val intent = ImageTransformIntent.SwitchFeature(it)
            vm.dispatch(intent)
        }

        RwHorizontalDivider()

        if ("QRCode Generate" == state.feature) {
            GenerateQRCodeView(vm, state)
        } else if ("Base64 to Image" == state.feature) {
            Base64ToImageView(vm, state)
        }

        RwVertical(height = Dimens.space_xxx)
    }

}

@Composable
private fun GenerateQRCodeView(vm: ImageTransformViewModel, state: ImageTransformState) {
    RwSubtitleText("Input Data")

    RwInputTextWithLength(modifier = UiUtils.modifierOutput, value = state.inputData, maxLength = Int.MAX_VALUE) {
        val intent = ImageTransformIntent.InputData(it)
        vm.dispatch(intent)
    }

    val imageBitmap = state.bitmap
    if (imageBitmap != null) {
        Image(modifier = UiUtils.modifierSpace_xxx, bitmap = imageBitmap, contentDescription = null)
    }

    RwTextCheckedButton(modifier = UiUtils.modifierSpace_xxx, text = "DONE") {
        vm.dispatch(intent = ImageTransformIntent.GenerateQRCode)
    }
}

@Composable
private fun Base64ToImageView(vm: ImageTransformViewModel, state: ImageTransformState) {
    val formats = listOf(DataFormat.Hex, DataFormat.Raw)
    val selected = formats.indexOf(state.format)
    RwRadioGroup(list = formats.map { it.code }, label = "Data Format", selected = selected) { format ->
        val intent = DataFormat.valueOf(format).let { ImageTransformIntent.SwitchFormat(it) }
        vm.dispatch(intent)
    }

    RwSubtitleText("Input Data")

    RwInputTextWithLength(modifier = UiUtils.modifierInput, value = state.inputData, maxLength = Int.MAX_VALUE, hint = "Remove the 'data:image/png;base64' prefix") {
        val intent = ImageTransformIntent.InputData(it)
        vm.dispatch(intent)
    }

    val imageBitmap = state.bitmap
    if (imageBitmap != null) {
        Image(modifier = UiUtils.modifierSpace_xxx, bitmap = imageBitmap, contentDescription = null)
    }

    RwTextCheckedButton(modifier = UiUtils.modifierSpace_xxx, text = "DONE") {
        vm.dispatch(intent = ImageTransformIntent.Base64ToImage)
    }
}