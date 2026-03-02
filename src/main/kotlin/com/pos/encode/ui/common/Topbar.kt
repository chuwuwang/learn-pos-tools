package com.pos.encode.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pos.encode.theme.AppTheme
import com.pos.encode.ui.theme.Dimens
import com.pos.encode.ui.theme.Fonts

@Composable
fun Topbar(content: @Composable RowScope.() -> Unit) {
    val modifier = Modifier.fillMaxWidth().height(Dimens.item_lg)
    Row(modifier = modifier, content = content)
}

@Composable
fun RowScope.TopbarItemView(text: String, index: Int, selectIndex: Int, onClick: () -> Unit) {
    BoxWithConstraints(Modifier.weight(1.0f).fillMaxHeight().clickable(onClick = onClick), contentAlignment = Alignment.Center) {
        val textColor = if (selectIndex == index) AppTheme.colors.mainTheme else AppTheme.colors.textMain
        Text(textAlign = TextAlign.Center, color = textColor, fontSize = Dimens.sp_title, text = text, fontFamily = Fonts.bold)

        Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
            if (selectIndex == index) Indicator()
        }
    }
}

@Composable
private fun Indicator() {
    Divider(modifier = Modifier.height(3.dp).padding(horizontal = Dimens.space_x), color = AppTheme.colors.mainTheme)
}