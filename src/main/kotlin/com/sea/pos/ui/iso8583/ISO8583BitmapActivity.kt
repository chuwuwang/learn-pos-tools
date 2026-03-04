package com.sea.pos.ui.iso8583

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.sea.pos.AppController
import com.sea.pos.flow.Event
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.viewModel
import com.sea.pos.ui.widget.*

@Composable
fun ISO8583BitmapActivity(modifier: Modifier = Modifier, controller: AppController) {
    val vm = viewModel(controller) {
        ISO8583BitmapViewModel()
    }
    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) {
        vm.event.collect { event ->
            if (event is Event.ShowToast) {

            }
        }
    }

    Column(modifier) {
        BitmapView(state.bitmapBooleans) {
            val intent = ISO8583BitmapIntent.ClickBitmapItem(it)
            vm.dispatch(intent)
        }

        RwSubtitleText("Bitmap")

        RwInputField(Dimens.item_norm, state.bitmapString, state.bitmapString.length, true) {
            val intent = ISO8583BitmapIntent.InputBitmap(it)
            vm.dispatch(intent)
        }

        Row(UiUtils.modifierSpace_xxx) {
            RwDecryptButton { }

            Horizontal(Dimens.space_x)

            RwErrorButton(text = "RESET") { }
        }

    }
}

@Composable
private fun BitmapView(bitmaps: BooleanArray, onItemClick: (Int) -> Unit) {
    val modifier = Modifier.fillMaxWidth()
        .padding(start = Dimens.space_xxx, top = Dimens.space_xxx, end = Dimens.space_xxx)
        .border(Dimens.divider, AppTheme.AppColors.divider, UiUtils.roundedCornerShape_8)
    LazyVerticalGrid(GridCells.Fixed(16), modifier = modifier) {
        val itemContent: @Composable (LazyGridItemScope.(Int) -> Unit) = { i ->
            val index = i + 1
            Column {
                val selected = bitmaps[index]
                val modifier = if (selected) {
                    Modifier.height(Dimens.item_norm).background(AppTheme.AppColors.buttonChecked)
                } else {
                    Modifier.height(Dimens.item_norm)
                }
                Row(modifier.clickable { onItemClick(index) }, verticalAlignment = Alignment.CenterVertically) {
                    ItemView("$index", index)
                }
                val is128Bit = bitmaps[1]
                if (is128Bit) {
                    if (index in 1..112) HorizontalDivider()
                } else {
                    if (index in 1..48) HorizontalDivider()
                }
            }
        }
        items(bitmaps.size - 1, itemContent = itemContent)
    }
}

@Composable
private fun RowScope.ItemView(text: String, position: Int) {
    val textStyle = TextStyle(color = AppTheme.AppColors.textSecondary, fontFamily = Fonts.medium, fontSize = Dimens.sp_text, textAlign = TextAlign.Center)
    Text(text, modifier = Modifier.wrapContentHeight(Alignment.CenterVertically).weight(1f), style = textStyle)
    if (position != 16 && position != 32 && position != 48 && position != 64 && position != 80 && position != 96 && position != 112 && position != 128) {
        Divider(modifier = Modifier.fillMaxHeight().width(Dimens.divider), color = AppTheme.AppColors.divider)
    }
}