package com.sea.pos.ui.widget

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.theme.AppTheme

@Composable
fun HorizontalDivider() {
    Divider(thickness = Dimens.divider, color = AppTheme.AppColors.divider)
}