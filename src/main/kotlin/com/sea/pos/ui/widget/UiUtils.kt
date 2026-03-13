package com.sea.pos.ui.widget

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.theme.AppTheme

object UiUtils {

    val modifierSpace: Modifier
        get() = Modifier.padding(start = Dimens.space_xxx, top = Dimens.space_norm, end = Dimens.space_xxx)

    val modifierSpace_xxx: Modifier
        get() = Modifier.padding(start = Dimens.space_xxx, top = Dimens.space_xxx, end = Dimens.space_xxx)

    val modifierInput: Modifier
        get() = Modifier.height(240.dp)

    val modifierOutput: Modifier
        get() = Modifier.height(120.dp)

    val roundedCornerShape_8: RoundedCornerShape
        get() = RoundedCornerShape(8.dp)

    val roundedCornerShape_16: RoundedCornerShape
        get() = RoundedCornerShape(16.dp)

    @Composable
    fun IconColor(index: Int, default: Int): Color {
        return if (index == default) AppTheme.AppColors.iconChecked else AppTheme.AppColors.icon
    }

}