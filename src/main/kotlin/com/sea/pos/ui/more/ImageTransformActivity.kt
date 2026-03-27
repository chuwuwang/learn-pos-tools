package com.sea.pos.ui.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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

        RwSubtitleText("Input Data")

        if ("QRCode Generate" == state.feature) {
            GenerateQRCode(vm, state)
        }

        RwVertical(height = Dimens.space_xxx)
    }

}

@Composable
private fun GenerateQRCode(vm: ImageTransformViewModel, state: ImageTransformState) {
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