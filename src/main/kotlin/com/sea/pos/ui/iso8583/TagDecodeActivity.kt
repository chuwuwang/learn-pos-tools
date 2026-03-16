package com.sea.pos.ui.iso8583

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sea.pos.emv.TagDecode
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.widget.*

@Composable
fun TagDecodeActivity() {

    val vm = remember { TagDecodeViewModel() }
    val state by vm.state.collectAsState()

    Column {
        TagDecodeView(vm, state)

        RwSubtitleText(state.tag.name)

        RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.inputData, state.tag.length, singleLine = true) {
            val intent = TagDecodeIntent.InputData(it)
            vm.dispatch(intent)
        }

        RwDecryptButton(modifier = UiUtils.modifierSpace_xxx) {
            vm.dispatch(TagDecodeIntent.Decode)
        }

        Vertical(height = Dimens.space_xxx)
    }
}

@Composable
private fun TagDecodeView(vm: TagDecodeViewModel, state: TagDecodeState) {
    val modifier = UiUtils.modifierSpace_xxx
        .fillMaxWidth()
        .clip(UiUtils.roundedCornerShape_8)
        .border(width = 2.dp, color = AppTheme.AppColors.dividerChecked, shape = UiUtils.roundedCornerShape_8)
        .padding(horizontal = Dimens.space_norm, vertical = Dimens.space_norm)
    Column(modifier) {
        val modifier = Modifier.height(Dimens.item_norm)
        val tagTVR = "Tag " + TagDecode.TVR.tag + " - " + TagDecode.TVR.description
        val tagAIP = "Tag " + TagDecode.AIP.tag + " - " + TagDecode.AIP.description
        val tagCVM = "Tag " + TagDecode.CVM.tag + " - " + TagDecode.CVM.description
        val tagCTQ = "Tag " + TagDecode.CTQ.tag + " - " + TagDecode.CTQ.description
        RwRadioButton(modifier = modifier, text = tagTVR, selected = state.tag == TagDecode.TVR) {
            val intent = TagDecodeIntent.SwitchTag(TagDecode.TVR)
            vm.dispatch(intent)
        }
        RwRadioButton(modifier = modifier, text = tagAIP, selected = state.tag == TagDecode.AIP) {
            val intent = TagDecodeIntent.SwitchTag(TagDecode.AIP)
            vm.dispatch(intent)
        }
        RwRadioButton(modifier = modifier, text = tagCVM, selected = state.tag == TagDecode.CVM) {
            val intent = TagDecodeIntent.SwitchTag(TagDecode.CVM)
            vm.dispatch(intent)
        }
        RwRadioButton(modifier = modifier, text = tagCTQ, selected = state.tag == TagDecode.CTQ) {
            val intent = TagDecodeIntent.SwitchTag(TagDecode.CTQ)
            vm.dispatch(intent)
        }
    }
}