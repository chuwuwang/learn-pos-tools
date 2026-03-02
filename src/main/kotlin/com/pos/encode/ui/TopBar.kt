package com.pos.encode.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pos.encode.theme.AppTheme
import com.pos.encode.ui.theme.Dimens
import com.pos.encode.ui.theme.boldFontFamily

object TopBar {

    @Composable
    fun showTopBar(content: @Composable RowScope.() -> Unit) {
        val modifier = Modifier.fillMaxWidth().height(Dimens.topBarHeight).background(AppTheme.colors.bgContent)
        Row(modifier = modifier, content = content)
    }

    @Composable
    fun RowScope.topBarItemView(text: String, index: Int, selectIndex: Int, onClick: () -> Unit) {
        BoxWithConstraints(Modifier.weight(1.0f).fillMaxHeight().clickable(onClick = onClick), contentAlignment = Alignment.Center) {
            val textColor = getTextColor(index, selectIndex)
            val dividerColor = getDividerColor(index, selectIndex)
            Text(textAlign = TextAlign.Center, color = textColor, fontSize = Dimens.contentSize, text = text, fontFamily = boldFontFamily)
            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) { itemDivider(dividerColor) }
        }
    }

    @Composable
    private fun itemDivider(color: Color) {
        Divider(modifier = Modifier.height(3.dp).width(80.dp), color = color)
    }

    @Composable
    private fun getTextColor(index: Int, selectIndex: Int): Color {
        return if (selectIndex == index) AppTheme.colors.topBarTextChecked else AppTheme.colors.topBarText
    }

    @Composable
    private fun getDividerColor(index: Int, selectIndex: Int): Color {
        return if (selectIndex == index) AppTheme.colors.topBarDividerChecked else AppTheme.colors.topBarDivider
    }


    @Composable
    fun item(modifier: Modifier, text: String, index: Int, selectIndex: Int, onClick: () -> Unit) {
        BoxWithConstraints(modifier.fillMaxHeight().clickable(onClick = onClick), contentAlignment = Alignment.Center) {
            val textColor = getTextColor(index, selectIndex)
            val dividerColor = getDividerColor(index, selectIndex)
            Text(
                textAlign = TextAlign.Center,
                color = textColor,
                fontSize = Dimens.contentSize,
                text = text,
                fontFamily = boldFontFamily
            )
            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) { itemDivider(dividerColor) }
        }
    }

    @Composable
    fun divider() {
        Divider(modifier = Modifier.fillMaxWidth().height(Dimens.dividerHeight), color = AppTheme.colors.divider)
    }





}