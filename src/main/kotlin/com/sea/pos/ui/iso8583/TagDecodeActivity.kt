package com.sea.pos.ui.iso8583

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sea.pos.AppController
import com.sea.pos.emv.TagDecode
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.viewModel
import com.sea.pos.ui.widget.*

@Composable
fun TagDecodeActivity(controller: AppController) {
    val vm = viewModel(container = controller) {
        TagDecodeViewModel()
    }
    val state by vm.state.collectAsState()

    val scrollState = rememberScrollState()
    val modifier = Modifier.verticalScroll(state = scrollState)
    Column(modifier) {
        TagView(vm, state)

        RwSubtitleText(state.tag.name)

        RwInputTextWithLength(modifier = Modifier.height(Dimens.item_norm), value = state.inputData, maxLength = state.tag.length, hint = state.tag.format, singleLine = true) {
            val intent = TagDecodeIntent.InputData(it)
            vm.dispatch(intent)
        }

        RwDecryptButton(modifier = UiUtils.modifierSpace_xxx) {
            vm.dispatch(intent = TagDecodeIntent.Decode)
        }

        TagDecodeView(state = state)
    }

}

@Composable
private fun TagView(vm: TagDecodeViewModel, state: TagDecodeState) {
    val tags = listOf(
        TagDecode.TVR, TagDecode.AIP, TagDecode.TerminalCapabilities, TagDecode.CVM,
        TagDecode.CTQ, TagDecode.TTQ, TagDecode.TSI, TagDecode.ATC, TagDecode.AUC,
    )
    val scrollState = rememberScrollState()
    val modifier = UiUtils.modifierSpace_xxx
        .height(280.dp)
        .fillMaxWidth()
        .verticalScroll(state = scrollState)
        .clip(shape = UiUtils.roundedCornerShape_8)
        .border(width = 2.dp, color = AppTheme.AppColors.dividerChecked, shape = UiUtils.roundedCornerShape_8)
        .padding(horizontal = Dimens.space_norm, vertical = Dimens.space_norm)
    Column(modifier) {
        val modifier = Modifier.height(Dimens.item_norm)
        tags.forEach { item ->
            val text = "Tag " + item.tag + " - " + item.description
            RwRadioButton(modifier = modifier, text = text, selected = state.tag == item) {
                val intent = TagDecodeIntent.SwitchTag(item)
                vm.dispatch(intent)
            }
        }
    }
}