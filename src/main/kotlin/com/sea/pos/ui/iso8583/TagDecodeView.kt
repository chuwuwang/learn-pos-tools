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
import com.sea.pos.emv.*
import com.sea.pos.extension.valid
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.widget.*
import com.sea.pos.utils.NumberUtils

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
        } else if (state.tag == TagDecode.TerminalCapabilities && position == 1) {
            subTitle += " - Card Data Input Capability"
        } else if (state.tag == TagDecode.TerminalCapabilities && position == 2) {
            subTitle += " - CVM Capability"
        } else if (state.tag == TagDecode.TerminalCapabilities && position == 3) {
            subTitle += " - Security Capability"
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
        } else if (tag == TagDecode.TerminalCapabilities) {
            val item = TerminalCapabilities.entries.find { it.position == "$position$idx" }
            if (item != null) desc = item.code
        } else if (tag == TagDecode.CTQ) {
            val item = CTQ.entries.find { it.position == "$position$idx" }
            if (item != null) desc = item.code
        } else if (tag == TagDecode.TTQ) {
            val item = TTQ.entries.find { it.position == "$position$idx" }
            if (item != null) desc = item.code
        } else if (tag == TagDecode.TSI) {
            val item = TSI.entries.find { it.position == "$position$idx" }
            if (item != null) desc = item.code
        } else if (tag == TagDecode.ATC) {
            val item = ATC.entries.find { it.position == "$position$idx" }
            if (item != null) desc = item.code
        } else if (tag == TagDecode.AUC) {
            val item = AUC.entries.find { it.position == "$position$idx" }
            if (item != null) desc = item.code
        }
        ItemView(value = bool, description = desc, position = idx)
    }
}

@Composable
private fun CVMView(list: List<Boolean>, position: Int) {
    val hexString = NumberUtils.booleans2HexString(list)
    if (position == 1) {
        ItemView(value = false, description = CVM.Performed1.code, position = 8)
        val bool = list[1]
        if (bool) {
            ItemView(value = false, description = CVM.Performed2.code, position = 7)
        } else {
            ItemView(value = false, description = CVM.Performed3.code, position = 7)
        }
        val string = NumberUtils.formatLast6Bits(hexString)
        val item = CVM.entries.find { it.position == string } ?: CVM.PerformedRFU
        ItemView(value = true, description = "$string - " + item.code, position = 1, single = true, head = "bit 6-1")
    } else if (position == 2) {
        val item = CVM.entries.find { it.position == "$position$hexString" } ?: CVM.ConditionRFU
        ItemView(value = false, description = "$hexString - " + item.code, position = 1, head = "bit 8-1")
    } else if (position == 3) {
        val item = CVM.entries.find { it.position == "$position$hexString" } ?: CVM.ResultRFU
        ItemView(value = false, description = "$hexString - " + item.code, position = 1, head = "bit 8-1")
    }
}

@Composable
private fun ItemView(value: Boolean, description: String, position: Int, single: Boolean = false, head: String = "") {
    Row(modifier = Modifier.height(32.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        val style = TextStyle(color = AppTheme.AppColors.textSecondary, fontFamily = Fonts.regular, fontSize = Dimens.sp_text, textAlign = TextAlign.Center)

        Text(text = if (head.valid) head else "bit $position", modifier = Modifier.width(60.dp), style = style)

        RwVerticalDivider()

        Text(text = if (value) "1" else "0", modifier = Modifier.width(60.dp), style = style)

        RwVerticalDivider()

        var modifier = Modifier.fillMaxHeight().fillMaxWidth()
        if (value) {
            if (single) {
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