package com.sea.pos.ui.emv

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.sea.pos.AppController
import com.sea.pos.emv.EMVTagCollections
import com.sea.pos.tlv.TLV
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.viewModel
import com.sea.pos.ui.widget.*

@Composable
fun TLVDecodeActivity(controller: AppController) {
    val vm = viewModel(container = controller) {
        TLVDecodeViewModel()
    }
    val state by vm.state.collectAsState()

    Column {
        RwSubtitleText("Input Data")

        RwInputTextWithLength(modifier = Modifier.weight(1f), value = state.inputData) {
            val intent = TLVDecodeIntent.InputData(it)
            vm.dispatch(intent)
        }

        RwSubtitleText("Output Data")

        DecodeView(state = state)

        Row(modifier = UiUtils.modifierSpace_xxx) {
            RwDecryptButton { vm.dispatch(intent = TLVDecodeIntent.Decode) }

            RwHorizontal(width = Dimens.space_x)

            RwDecryptButton { }
        }

        RwVertical(height = Dimens.space_xxx)
    }

}

@Composable
private fun ColumnScope.DecodeView(state: TLVDecodeState) {
    val modifier = UiUtils.modifierSpace.weight(3f).fillMaxWidth()
        .border(width = Dimens.divider, color = AppTheme.AppColors.divider, shape = UiUtils.roundedCornerShape_8)
    Column(modifier) {
        Row(modifier = Modifier.height(IntrinsicSize.Min), verticalAlignment = Alignment.CenterVertically) {
            ItemLabel("Tag")
            ItemLabel("Length")
            ItemLabel("Value", weight = 3f)
            ItemLabel("Description", weight = 3f)
            ItemLabel("Source", divider = false)
        }

        RwHorizontalDivider()

        Box {
            val bindItem: @Composable (TLV, Int) -> Unit = { item, position ->
                SelectionContainer {
                    val tag = item.tag
                    val value = item.value
                    val length = item.length.toString()
                    val pair = EMVTagCollections.map[tag] ?: Pair("", "")
                    val modifier = if (position % 2 != 0) {
                        Modifier.height(IntrinsicSize.Min).background(color = AppTheme.AppColors.tertiary)
                    } else {
                        Modifier.height(IntrinsicSize.Min)
                    }
                    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
                        ItemView(text = tag)
                        ItemView(text = length)
                        ItemView(text = value, weight = 3f, TextAlign.Start)
                        ItemView(text = pair.first, weight = 3f, TextAlign.Start)
                        ItemView(text = pair.second, divider = false)
                    }
                }
                RwHorizontalDivider()
            }
            val scrollState = rememberLazyListState()
            LazyColumn(state = scrollState) {
                itemsIndexed(state.outputData) { position, item -> bindItem(item, position) }
            }

            val style = defaultScrollbarStyle().copy(
                hoverColor = AppTheme.AppColors.dividerChecked,
                unhoverColor = AppTheme.AppColors.dividerChecked.copy(alpha = 0.5f),
            )
            val adapter = rememberScrollbarAdapter(scrollState = scrollState)
            VerticalScrollbar(modifier = Modifier.fillMaxHeight().align(Alignment.CenterEnd), style = style, adapter = adapter)
        }
    }
}

@Composable
private fun RowScope.ItemView(text: String, weight: Float = 1f, textAlign: TextAlign = TextAlign.Center, divider: Boolean = true) {
    val modifier = Modifier.wrapContentHeight(Alignment.CenterVertically).weight(weight)
        .padding(horizontal = Dimens.space_norm, vertical = Dimens.space_x)
    val style = TextStyle(color = AppTheme.AppColors.textSecondary, fontFamily = Fonts.regular, fontSize = Dimens.sp_text, textAlign = textAlign)
    Text(text = text, modifier = modifier, style = style)
    if (divider) RwVerticalDivider()
}

@Composable
private fun RowScope.ItemLabel(label: String, weight: Float = 1f, divider: Boolean = true) {
    val modifier = Modifier.wrapContentHeight(Alignment.CenterVertically).weight(weight)
        .padding(horizontal = Dimens.space_norm, vertical = Dimens.space_x)
    val style = TextStyle(color = AppTheme.AppColors.textMain, fontFamily = Fonts.medium, fontSize = Dimens.sp_text, textAlign = TextAlign.Center)
    Text(text = label, modifier, style = style)
    if (divider) RwVerticalDivider()
}