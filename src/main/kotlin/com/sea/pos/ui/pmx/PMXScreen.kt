package com.sea.pos.ui.pmx

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.widget.*

@Composable
fun PMXScreen() {
    val features = listOf(PMXFeature.ACTIVE, PMXFeature.GPS_COORDINATE)

    val vm = remember { PMXViewModel() }
    val state by vm.state.collectAsState()

    Column {
        val selectedFeature = features.indexOf(state.feature)
        Topbar(list = features.map { it.code }, selected = selectedFeature) { feat ->
            val item = PMXFeature.entries.find { it.code == feat } ?: PMXFeature.ACTIVE
            val intent = PMXIntent.SwitchFeature(item)
            vm.dispatch(intent)
        }

        RwHorizontalDivider()

        if (state.feature == PMXFeature.ACTIVE) {
            ActiveScreen(vm, state)
        } else if (state.feature == PMXFeature.GPS_COORDINATE) {
            CoordinateScreen(vm, state)
        }

    }

}

@Composable
private fun ActiveScreen(vm: PMXViewModel, state: PMXState) {
    Row {
        val modifier = Modifier.weight(1f)
        Row(modifier = modifier) {
            SubtitleText("Device Model")
            RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.activateReq.model, singleLine = true, showLength = false) {
                val newReq = state.activateReq.copy(model = it)
                val intent = PMXIntent.InputActiveParameter(newReq)
                vm.dispatch(intent)
            }
        }
        Row(modifier = modifier) {
            SubtitleText("Device Vendor")
            RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.activateReq.vendor, singleLine = true, showLength = false) {
                val newReq = state.activateReq.copy(vendor = it)
                val intent = PMXIntent.InputActiveParameter(newReq)
                vm.dispatch(intent)
            }
        }
    }

    Row {
        val modifier = Modifier.weight(1f)
        Row(modifier = modifier) {
            SubtitleText("Device SN")
            RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.activateReq.serialNumber, singleLine = true, showLength = false) {
                val newReq = state.activateReq.copy(serialNumber = it)
                val intent = PMXIntent.InputActiveParameter(newReq)
                vm.dispatch(intent)
            }
        }
        Row(modifier = modifier) {
            SubtitleText("Active Code")
            RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.activateReq.encryptedPin, singleLine = true, showLength = false) {
                val newReq = state.activateReq.copy(encryptedPin = it)
                val intent = PMXIntent.InputActiveParameter(newReq)
                vm.dispatch(intent)
            }
        }
    }

    Row {
        SubtitleText("URL")
        RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.requestUrl, singleLine = true, showLength = false) {
            val intent = PMXIntent.InputRequestUrl(text = it)
            vm.dispatch(intent)
        }
    }

    RwTextCheckedButton(modifier = UiUtils.modifierSpace_xxx, text = "ACTIVE") {
        vm.dispatch(intent = PMXIntent.Active)
    }

    Row {
        SubtitleText("DeviceId")
        RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.activateInfo.deviceId ?: "", enabled = false, singleLine = true, showLength = false) {}
    }

    RwSubtitleText("RSA PublicKey")
    RwInputTextWithLength(modifier = Modifier.height(144.dp), value = state.publicKey, enabled = false, showLength = false) {}

    RwSubtitleText("RSA PrivateKey")
    RwInputTextWithLength(modifier = Modifier.height(240.dp), value = state.privateKey, enabled = false, showLength = false) {}

}

@Composable
private fun CoordinateScreen(vm: PMXViewModel, state: PMXState) {
    val types = listOf(CoordinateType.BD09, CoordinateType.GCJ02)
    val selectedType = types.indexOf(state.coordinateType)
    RwRadioGroup(list = types.map { it.code }, label = "Coordinate Type", selected = selectedType) { code ->
        val item = types.find { it.code == code } ?: CoordinateType.BD09
        val intent = PMXIntent.SwitchCoordinateType(item)
        vm.dispatch(intent)
    }

    Row {
        val modifier = Modifier.weight(1f)
        Row(modifier = modifier) {
            SubtitleText("Latitude")
            RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.inputLat, singleLine = true, showLength = false) {
                vm.dispatch(intent = PMXIntent.InputLat(it))
            }
        }
        Row(modifier = modifier) {
            SubtitleText("Longitude")
            RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.inputLon, singleLine = true, showLength = false) {
                vm.dispatch(intent = PMXIntent.InputLon(it))
            }
        }
    }

    RwTextCheckedButton(modifier = UiUtils.modifierSpace_xxx, text = "CONVERT") {
        vm.dispatch(intent = PMXIntent.ConvertCoordinate)
    }

    RwVertical(Dimens.space_xxx)

    Row {
        SubtitleText("GCJ-02")
        RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.outputGcj02, enabled = false, singleLine = true, showLength = false) {}
    }

    Row {
        SubtitleText("WGS-84")
        RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.outputWgs84, enabled = false, singleLine = true, showLength = false) {}
    }

}

@Composable
private fun SubtitleText(text: String) {
    val style = TextStyle(fontSize = Dimens.sp_text, fontFamily = Fonts.bold, textAlign = TextAlign.Start, color = AppTheme.AppColors.textMain)
    Text(modifier = UiUtils.modifierSpace_xxx.width(96.dp), text = text, style = style)
}