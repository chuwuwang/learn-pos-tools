package com.sea.pos.ui.widget

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
import com.sea.pos.ui.resource.Strings
import com.sea.pos.ui.widget.Sidebar.MENU_COMMON_ALGORITHM
import com.sea.pos.ui.widget.Sidebar.MENU_ENCRYPTION_ALGORITHM
import com.sea.pos.ui.widget.Sidebar.MENU_HASH_ALGORITHM
import com.sea.pos.ui.widget.Sidebar.MENU_ISO8583_BITMAP

object Sidebar {

    const val MENU_HASH_ALGORITHM = 0

    const val MENU_ENCRYPTION_ALGORITHM = 1

    const val MENU_COMMON_ALGORITHM = 2

    const val MENU_ISO8583_BITMAP = 3

}

@Composable
fun Sidebar(modifier: Modifier, index: Int, onClick: (Int) -> Unit) {
    Column(modifier) {
        Vertical(Dimens.space_x)

        ItemView(Strings.hash_algorithm, UiUtils.getIconColor(index, MENU_HASH_ALGORITHM), resourcePath = "images/ic_menu_hash_algo_black.png") {
            onClick(MENU_HASH_ALGORITHM)
        }

        ItemView(Strings.encryption_algorithm, UiUtils.getIconColor(index, MENU_ENCRYPTION_ALGORITHM), resourcePath = "images/ic_menu_des_ago_black.png") {
            onClick(MENU_ENCRYPTION_ALGORITHM)
        }

        ItemView(Strings.common_algo, UiUtils.getIconColor(index, MENU_COMMON_ALGORITHM), resourcePath = "images/ic_menu_common_algo_black.png") {
            onClick(MENU_COMMON_ALGORITHM)
        }

        ItemView(Strings.iso8583_bitmap, UiUtils.getIconColor(index, MENU_ISO8583_BITMAP), resourcePath = "images/ic_menu_bitmap_black.png") {
            onClick(MENU_ISO8583_BITMAP)
        }

    }
}

@Composable
private fun ItemView(text: String, tint: Color, resourcePath: String, onClick: () -> Unit) {
    val modifier = Modifier.fillMaxWidth().height(Dimens.item_norm).clickable(onClick = onClick)
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Horizontal(Dimens.space_x)

        Icon(painter = painterResource(resourcePath), contentDescription = null, modifier = Modifier.size(24.dp), tint = tint)

        Horizontal(Dimens.space_norm)

        Text(color = tint, textAlign = TextAlign.Start, fontSize = Dimens.sp_title, fontFamily = Fonts.bold, text = text)
    }
}