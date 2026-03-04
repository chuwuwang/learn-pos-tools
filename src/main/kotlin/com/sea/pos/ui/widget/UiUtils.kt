package com.sea.pos.ui.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme

object UiUtils {

    @Composable
    fun getIconColor(index: Int, default: Int): Color {
        return if (index == default) AppTheme.colors.iconChecked else AppTheme.colors.icon
    }

    @Composable
    fun MediumMainTextStyle(): TextStyle {
        return TextStyle(fontFamily = Fonts.medium, fontSize = Dimens.sp_text, color = AppTheme.colors.textMain)
    }

    fun spaceModifier(): Modifier {
        return Modifier.padding(start = Dimens.space_xxx, top = Dimens.space_norm)
    }

    fun spaceXxxModifier(): Modifier {
        return Modifier.padding(start = Dimens.space_xxx, top = Dimens.space_xxx)
    }

}