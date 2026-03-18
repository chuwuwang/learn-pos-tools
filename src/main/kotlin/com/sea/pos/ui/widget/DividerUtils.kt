package com.sea.pos.ui.widget

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.theme.AppTheme

@Composable
fun RwHorizontalDivider() {
    HorizontalDivider(thickness = Dimens.divider, color = AppTheme.AppColors.divider)
}

@Composable
fun RwVerticalDivider() {
    VerticalDivider(thickness = Dimens.divider, color = AppTheme.AppColors.divider)
}