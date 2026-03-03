package com.pos.encode.com.pos.encode.ui.iso8583

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pos.encode.com.pos.encode.ui.CommonUiUtil
import com.pos.encode.com.pos.encode.ui.common.RwInput
import com.pos.encode.com.pos.encode.ui.widget.decryptButton
import com.pos.encode.com.pos.encode.ui.widget.errorButton
import com.pos.encode.com.pos.encode.ui.widget.horizontal
import com.sea.pos.theme.AppTheme
import com.sea.pos.ui.util.Vertical
import com.sea.pos.resource.Dimens
import com.sea.pos.resource.mediumFontFamily
import com.pos.encode.ui.widget.DialogHelper
import com.pos.encode.util.ByteUtil

@Composable
fun Bitmap8583Screen(modifier: Modifier) {
    val bitmapString = remember { mutableStateOf("0000000000000000") }
    val bitmapBooleans = remember {
        val bitmapBytes = ByteUtil.hexString2Bytes(bitmapString.value)
        val binaryBytes = ByteUtil.bytes2BinaryBytes(bitmapBytes)
        mutableStateOf(binaryBytes)
    }
    val dialogVisible = remember { mutableStateOf(false) }
    val maxBitmapLength = remember { mutableStateOf(bitmapString.value.length) }

    Column(modifier) {
        val modifierBitmap = Modifier.fillMaxWidth()
            .padding(start = Dimens.marginStart, top = Dimens.marginTop, end = Dimens.marginEnd)
            .border(Dimens.dividerHeight, AppTheme.colors.divider, CommonUiUtil.roundedCornerShapeWith8)
        val columns = GridCells.Fixed(16)
        LazyVerticalGrid(columns, modifier = modifierBitmap) {
            val itemContent: @Composable (LazyGridItemScope.(Int) -> Unit) = { i ->
                val index = i + 1
                val onItemClick = {
                    val bitmapBytes = getDynamicBitmap(index, bitmapBooleans.value)
                    bitmapString.value = ByteUtil.bytes2HexString(bitmapBytes)
                    bitmapBooleans.value = ByteUtil.bytes2BinaryBytes(bitmapBytes)
                    maxBitmapLength.value = bitmapString.value.length
                }
                Column {
                    val selected = bitmapBooleans.value[index]
                    val modifierItem = if (selected) {
                        Modifier.height(56.dp).background(AppTheme.colors.borderChecked)
                    } else {
                        Modifier.height(56.dp)
                    }
                    Row(modifierItem.clickable(onClick = onItemClick), verticalAlignment = Alignment.CenterVertically) { bind("$index", index) }
                    val bit128 = bitmapBooleans.value[1]
                    if (bit128) {
                        if (index in 1..112) CommonUiUtil.horizontalDivider()
                    } else {
                        if (index in 1..48) CommonUiUtil.horizontalDivider()
                    }
                }
            }
            items(bitmapBooleans.value.size - 1, itemContent = itemContent)
        }


        Vertical(Dimens.marginTop)

        RwInput.InputField("Bitmap", 72.dp, bitmapString.value, maxBitmapLength.value) { bitmapString.value = it }

        Vertical(Dimens.marginTop)

        Row {
            val reset = {
                val bitmapBytes = getInitializeBitmap()
                bitmapString.value = ByteUtil.bytes2HexString(bitmapBytes)
                bitmapBooleans.value = ByteUtil.bytes2BinaryBytes(bitmapBytes)
                maxBitmapLength.value = bitmapString.value.length
            }
            val decrypt = {
                val length = bitmapString.value.length
                if (length != 16 && length != 32) {
                    dialogVisible.value = true
                } else {
                    dialogVisible.value = false
                    val bitmapBytes = ByteUtil.hexString2Bytes(bitmapString.value)
                    bitmapBooleans.value = ByteUtil.bytes2BinaryBytes(bitmapBytes)
                    maxBitmapLength.value = bitmapString.value.length
                }
            }
            decryptButton { decrypt() }
            horizontal(16.dp)
            errorButton("RESET") { reset() }
        }

        DialogHelper.errorDialog("The size of the Bitmap can only be 16 or 32", dialogVisible)
    }
}

private fun getInitializeBitmap():ByteArray {
   return ByteUtil.hexString2Bytes("0000000000000000")
}

private fun getDynamicBitmap(index: Int, booleans: BooleanArray): ByteArray {
    val item = booleans[index]
    booleans[index] = !item
    if (index != 1) return ByteUtil.binaryBytes2Bytes(booleans)
    if (item) {
        val temp = ByteArray(8)
        val oldBytes = ByteUtil.binaryBytes2Bytes(booleans)
        System.arraycopy(oldBytes, 0, temp, 0, temp.size)
        return temp
    } else {
        val temp = ByteArray(16)
        val oldBytes = ByteUtil.binaryBytes2Bytes(booleans)
        System.arraycopy(oldBytes, 0, temp, 0, oldBytes.size)
        return temp
    }
}

@Composable
private fun RowScope.bind(text: String, position: Int) {
    val textStyle = TextStyle(color = AppTheme.colors.textSecondary, fontFamily = mediumFontFamily, fontSize = Dimens.titleSize, textAlign = TextAlign.Center)
    Text(text, modifier = Modifier.wrapContentHeight(Alignment.CenterVertically).weight(1f), style = textStyle)
    if (position != 16 && position != 32 && position != 48 && position != 64 && position != 80 && position != 96 && position != 112 && position != 128) {
        Divider(modifier = Modifier.fillMaxHeight().width(Dimens.dividerHeight), color = AppTheme.colors.divider)
    }
}