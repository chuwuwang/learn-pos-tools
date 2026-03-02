package com.pos.encode.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.pos.encode.theme.AppTheme

object ThemeUtils {

    @Composable
    fun getIconColor(index: Int, default: Int): Color {
        return if (index == default) AppTheme.colors.iconChecked else AppTheme.colors.icon
    }

}