package com.sea.pos.ui.iso8583

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.sea.pos.ui.widget.RwDecryptButton
import com.sea.pos.ui.widget.RwRadioButton
import com.sea.pos.ui.widget.UiUtils
import com.sea.pos.ui.widget.Vertical

@Composable
fun TagDecodeActivity() {

    val vm = remember { TagDecodeViewModel() }
    val state by vm.state.collectAsState()

    Column {
        TagDecodeView(vm, state)

        RwDecryptButton(modifier = UiUtils.modifierSpace_xxx) {}

        Vertical(Dimens.space_xxx)
    }
}

@Composable
private fun TagDecodeView(vm: TagDecodeViewModel, state: TagDecodeState) {
    val modifier = UiUtils.modifierSpace_xxx
        .fillMaxWidth()
        .clip(UiUtils.roundedCornerShape_8)
        .border(width = 2.dp, color = AppTheme.AppColors.dividerChecked, shape = UiUtils.roundedCornerShape_8)
    Column(modifier) {
        val height = Modifier.height(Dimens.item_norm)
        val tagTVR = "Tag " + TagDecode.TVR.tag + " - " + TagDecode.TVR.description
        val tagAIP = "Tag " + TagDecode.AIP.tag + " - " + TagDecode.AIP.description
        val tagCVM = "Tag " + TagDecode.CVM.tag + " - " + TagDecode.CVM.description
        val tagCTQ = "Tag " + TagDecode.CTQ.tag + " - " + TagDecode.CTQ.description
        RwRadioButton(modifier = height, text = tagTVR, selected = state.tag == TagDecode.TVR) {

        }
        RwRadioButton(modifier = height, text = tagAIP, selected = state.tag == TagDecode.AIP) {

        }
        RwRadioButton(modifier = height, text = tagCVM, selected = state.tag == TagDecode.CVM) {

        }
        RwRadioButton(modifier = height, text = tagCTQ, selected = state.tag == TagDecode.CTQ) {

        }
    }
}