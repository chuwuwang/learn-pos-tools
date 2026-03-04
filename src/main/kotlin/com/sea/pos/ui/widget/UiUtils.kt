package com.sea.pos.ui.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.theme.AppTheme

object UiUtils {

    val spaceModifier: Modifier
        get() = Modifier.padding(start = Dimens.space_xxx, top = Dimens.space_norm)

    val spaceXxxModifier: Modifier
        get() = Modifier.padding(start = Dimens.space_xxx, top = Dimens.space_xxx)

    @Composable
    fun IconColor(index: Int, default: Int): Color {
        return if (index == default) AppTheme.colors.iconChecked else AppTheme.colors.icon
    }

}