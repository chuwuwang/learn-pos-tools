package com.sea.pos.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme

@Composable
fun Topbar(list: List<String>, selection: Int = 0, onClick: (String) -> Unit) {
    val selected = remember { mutableStateOf(list[selection]) }
    val modifier = Modifier.fillMaxWidth().height(Dimens.item_lg)
    Row(modifier = modifier) {
        for (item in list) {
            ItemView(item, selected.value) {
                selected.value = item
                onClick(item)
            }
        }
    }
}

@Composable
private fun RowScope.ItemView(text: String, selected: String, onClick: () -> Unit) {
    BoxWithConstraints(Modifier.weight(1.0f).fillMaxHeight().clickable(onClick = onClick), contentAlignment = Alignment.Center) {
        val textColor = if (text == selected) AppTheme.colors.mainTheme else AppTheme.colors.textMain
        Text(textAlign = TextAlign.Center, color = textColor, fontSize = Dimens.sp_text, text = text, fontFamily = Fonts.bold)

        Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
            if (text == selected) Indicator()
        }
    }
}

@Composable
private fun Indicator() {
    Divider(modifier = Modifier.height(3.dp).padding(horizontal = Dimens.space_x), color = AppTheme.colors.mainTheme)
}