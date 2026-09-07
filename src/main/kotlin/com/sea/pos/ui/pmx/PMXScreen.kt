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
                state.activateReq.model = it
                val intent = PMXIntent.InputActiveParameter(state.activateReq)
                vm.dispatch(intent)
            }
        }
        Row(modifier = modifier) {
            SubtitleText("Device Vendor")
            RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.requestUrl, singleLine = true, showLength = false) {
                state.activateReq.vendor = it
                val intent = PMXIntent.InputActiveParameter(state.activateReq)
                vm.dispatch(intent)
            }
        }
    }

    Row {
        val modifier = Modifier.weight(1f)
        Row(modifier = modifier) {
            SubtitleText("Device SN")
            RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.requestUrl, singleLine = true, showLength = false) {
                state.activateReq.serialNumber = it
                val intent = PMXIntent.InputActiveParameter(state.activateReq)
                vm.dispatch(intent)
            }

        }
        Row(modifier = modifier) {
            SubtitleText("Active Code")
            RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.requestUrl, singleLine = true, showLength = false) {
                val intent = PMXIntent.InputRequestUrl(text = it)
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

    RwSubtitleText("RSA PublicKey")

    RwInputTextWithLength(modifier = UiUtils.modifierOutput, value = state.publicKey, showLength = false) {
        val intent = PMXIntent.OutputPublicKey(text = it)
        vm.dispatch(intent)
    }

    RwSubtitleText("RSA PrivateKey")

    RwInputTextWithLength(modifier = UiUtils.modifierOutput, value = state.privateKey, showLength = false) {
        val intent = PMXIntent.OutputPrivateKey(text = it)
        vm.dispatch(intent)
    }

}

@Composable
private fun SubtitleText(text: String) {
    val style = TextStyle(fontSize = Dimens.sp_text, fontFamily = Fonts.bold, textAlign = TextAlign.Start, color = AppTheme.AppColors.textMain)
    Text(modifier = UiUtils.modifierSpace_xxx.width(96.dp), text = text, style = style)
}