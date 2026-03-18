package com.sea.pos.ui.iso8583

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sea.pos.emv.AIP
import com.sea.pos.emv.CTQ
import com.sea.pos.emv.TVR
import com.sea.pos.emv.TagDecode
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.widget.*

@Composable
fun TagDecodeView(state: TagDecodeState) {
    state.outputData.withIndex().forEach { (index, list) ->
        val position = index + 1
        var subTitle = "Byte $position"
        if (state.tag == TagDecode.CVM && position == 1) {
            subTitle += " - CVM Performed"
        } else if (state.tag == TagDecode.CVM && position == 2) {
            subTitle += " - CVM Condition"
        } else if (state.tag == TagDecode.CVM && position == 3) {
            subTitle += " - CVM Result"
        }
        RwSubtitleText(subTitle)

        val modifier = UiUtils.modifierSpace
            .fillMaxWidth()
            .border(width = Dimens.divider, color = AppTheme.AppColors.divider, shape = UiUtils.roundedCornerShape_8)
        Column(modifier) {
            if (state.tag == TagDecode.CVM) {
                CVMView(list, position)
            } else {
                DecodeView(state.tag, list, position)
            }
        }
    }

    RwVertical(height = Dimens.space_xxx)
}

@Composable
private fun DecodeView(tag: TagDecode, list: List<Boolean>, position: Int) {
    list.forEachIndexed { index, bool ->
        var desc = ""
        val idx = 8 - index
        if (tag == TagDecode.TVR) {
            val item = TVR.entries.find { it.position == "$position$idx" }
            if (item != null) desc = item.code
        } else if (tag == TagDecode.AIP) {
            val item = AIP.entries.find { it.position == "$position$idx" }
            if (item != null) desc = item.code
        } else if (tag == TagDecode.CTQ) {
            val item = CTQ.entries.find { it.position == "$position$idx" }
            if (item != null) desc = item.code
        }
        ItemView(value = bool, description = desc, position = idx)
    }
}

@Composable
private fun CVMView(list: List<Boolean>, position: Int) {

}

@Composable
private fun ItemView(value: Boolean, description: String, position: Int, all: Boolean = false) {
    Row(modifier = Modifier.height(32.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        val style = TextStyle(color = AppTheme.AppColors.textSecondary, fontFamily = Fonts.regular, fontSize = Dimens.sp_text, textAlign = TextAlign.Center)

        Text(text = "bit $position", modifier = Modifier.width(60.dp), style = style)

        RwVerticalDivider()

        Text(text = if (value) "1" else "0", modifier = Modifier.width(60.dp), style = style)

        RwVerticalDivider()

        var modifier = Modifier.fillMaxHeight().fillMaxWidth()
        if (value) {
            if (all) {
                modifier = modifier.clip(shape = UiUtils.roundedCornerShape_8)
            } else if (position == 8) {
                val shape = RoundedCornerShape(topEnd = 8.dp)
                modifier = modifier.clip(shape)
            } else if (position == 1) {
                val shape = RoundedCornerShape(bottomEnd = 8.dp)
                modifier = modifier.clip(shape)
            }
            modifier = modifier.background(color = AppTheme.AppColors.tertiary)
        }
        Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {
            Text(text = description, modifier = Modifier.padding(horizontal = Dimens.space_x), style = style)
        }
    }

    if (position == 1) return

    RwHorizontalDivider()
}