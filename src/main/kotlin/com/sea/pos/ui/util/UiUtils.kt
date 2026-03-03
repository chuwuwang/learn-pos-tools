package com.sea.pos.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sea.pos.theme.AppTheme

object UiUtils {

    @Composable
    fun getIconColor(index: Int, default: Int): Color {
        return if (index == default) AppTheme.colors.iconChecked else AppTheme.colors.icon
    }

}