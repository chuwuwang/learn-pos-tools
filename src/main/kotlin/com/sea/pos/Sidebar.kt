package com.sea.pos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.widget.Horizontal
import com.sea.pos.ui.widget.UiUtils
import com.sea.pos.ui.widget.Vertical

@Composable
fun Sidebar(modifier: Modifier, index: Int, onClick: (Int) -> Unit) {
    Column(modifier) {
        Vertical(Dimens.space_x)

        var resourcePath = "images/ic_menu_hash_algo_black.png"
        var tint = UiUtils.IconColor(index, Sidebar.MENU_ALGO_HASH)
        ItemView("Hash", tint = tint, resourcePath = resourcePath) {
            onClick(Sidebar.MENU_ALGO_HASH)
        }

        resourcePath = "images/ic_menu_des_ago_black.png"
        tint = UiUtils.IconColor(index, Sidebar.MENU_ALGO_DES)
        ItemView("DES / 3DES", tint = tint, resourcePath = resourcePath) {
            onClick(Sidebar.MENU_ALGO_DES)
        }

        resourcePath = "images/ic_menu_common_algo_black.png"
        tint = UiUtils.IconColor(index, Sidebar.MENU_ALGO_COMMON)
        ItemView("Common Crypto", tint = tint, resourcePath = resourcePath) {
            onClick(Sidebar.MENU_ALGO_COMMON)
        }

        resourcePath = "images/ic_menu_bitmap_black.png"
        tint = UiUtils.IconColor(index, Sidebar.MENU_ISO8583)
        ItemView("ISO8583 Bitmap", tint = tint, resourcePath = resourcePath) {
            onClick(Sidebar.MENU_ISO8583)
        }

        resourcePath = "images/ic_menu_tag_decode_black.png"
        tint = UiUtils.IconColor(index, Sidebar.MENU_TAG_DECODE)
        ItemView("EMV Tag Decoder", tint = tint, resourcePath = resourcePath) {
            onClick(Sidebar.MENU_TAG_DECODE)
        }
    }
}

@Composable
private fun ItemView(text: String, tint: Color, resourcePath: String, onClick: () -> Unit) {
    val modifier = Modifier.fillMaxWidth().height(Dimens.item_norm).clickable(onClick = onClick)
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Horizontal(Dimens.space_x)

        Icon(painter = painterResource(resourcePath), modifier = Modifier.size(24.dp), contentDescription = null, tint = tint)

        Horizontal(Dimens.space_norm)

        Text(color = tint, textAlign = TextAlign.Start, fontSize = Dimens.sp_title, fontFamily = Fonts.bold, text = text)
    }
}

object Sidebar {

    const val MENU_ALGO_HASH = 0

    const val MENU_ALGO_AES = 1

    const val MENU_ALGO_DES = 2

    const val MENU_ALGO_COMMON = 3

    const val MENU_ISO8583 = 10

    const val MENU_TAG_DECODE = 11

}