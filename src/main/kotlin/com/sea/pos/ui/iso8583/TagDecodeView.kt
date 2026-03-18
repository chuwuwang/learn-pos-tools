package com.sea.pos.ui.iso8583

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pos.encode.util.ByteUtil
import com.sea.pos.emv.TagDecode
import com.sea.pos.extension.empty
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.widget.*

@Composable
fun TagDecodeView(state: TagDecodeState) {
    if (state.outputData.empty) return
    val bytes = ByteUtil.hexString2Bytes(state.outputData)
    val booleans = ByteUtil.bytes2BinaryBytes(bytes).toList().chunked(size = 8)

    val scrollState = rememberScrollState()
    val modifier = Modifier.verticalScroll(state = scrollState)
    Column(modifier) {
        booleans.withIndex().forEach { (index, list) ->
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

            val modifier = UiUtils.modifierSpace.fillMaxWidth().border(
                width = Dimens.divider, color = AppTheme.AppColors.divider, shape = UiUtils.roundedCornerShape_8
            )
            Column(modifier) {
                if (state.tag == TagDecode.TVR) {
                    TVRView(list, position)
                } else if (state.tag == TagDecode.AIP) {
                    AIPView(list, position)
                } else if (state.tag == TagDecode.CVM) {
                    CVMView(list, position)
                } else if (state.tag == TagDecode.CTQ) {
                    CTQView(list, position)
                }
            }

        }

    }

    RwVertical(height = Dimens.space_xxx)
}

@Composable
private fun TVRView(list: List<Boolean>, position: Int) {

}

@Composable
private fun AIPView(list: List<Boolean>, position: Int) {
    if (position == 1) {
        ItemView(position = "bit 8", list[0], "RFU")
        ItemView(position = "bit 7", list[1], "SDA supported")
        ItemView(position = "bit 6", list[2], "DDA supported")
        ItemView(position = "bit 5", list[3], "Cardholder verification is supported")
        ItemView(position = "bit 4", list[4], "Terminal risk management is to be performed")
        ItemView(position = "bit 3", list[5], "Issuer authentication is supported")
        ItemView(position = "bit 2", list[6], "RFU")
        ItemView("bit 1", list[7], "CDA supported")
    } else if (position == 2) {
        ItemView("bit 8", list[0], "Reserved for use by the EMV Contactless Specifications")
        ItemView("bit 7", list[1], "RFU")
        ItemView("bit 6", list[2], "RFU")
        ItemView("bit 5", list[3], "RFU")
        ItemView("bit 4", list[4], "RFU")
        ItemView("bit 3", list[5], "RFU")
        ItemView("bit 2", list[6], "RFU")
        ItemView("bit 1", list[7], "RFU")
    }
}

@Composable
private fun CVMView(list: List<Boolean>, position: Int) {

}

@Composable
private fun CTQView(list: List<Boolean>, position: Int) {

}

@Composable
private fun ItemView(position: String, value: Boolean, description: String, last: Boolean = false) {
    var text: String
    val color = AppTheme.AppColors.dividerChecked
    val modifier = if (value) {
        text = "1"
        if (last) {
            Modifier.height(48.dp).fillMaxWidth().clip(UiUtils.roundedCornerShape_8).background(color = color)
        } else if (position == "bit 8") {
            val shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
            Modifier.height(48.dp).fillMaxWidth().clip(shape).background(color = color)
        } else if (position == "bit 1") {
            val shape = RoundedCornerShape(bottomStart = 4.dp, bottomEnd = 4.dp)
            Modifier.height(48.dp).fillMaxWidth().clip(shape).background(color = color)
        } else {
            Modifier.height(48.dp).fillMaxWidth().background(color = color)
        }
    } else {
        text = "0"
        Modifier.height(48.dp).fillMaxWidth()
    }

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        val style = TextStyle(
            color = AppTheme.AppColors.textSecondary,
            fontFamily = Fonts.medium,
            fontSize = Dimens.sp_text,
            textAlign = TextAlign.Center
        )

        Text(text = position, modifier = Modifier.width(80.dp), style = style)

        RwVerticalDivider()

        Text(text = text, modifier = Modifier.width(80.dp), style = style)

        RwVerticalDivider()

        Text(text = description, modifier = Modifier.padding(horizontal = 16.dp), style = style)
    }


    if (position == "bit 1") return

    RwHorizontalDivider()
}