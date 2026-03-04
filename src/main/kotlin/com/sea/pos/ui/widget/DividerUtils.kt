package com.sea.pos.ui.widget

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sea.pos.ui.theme.AppTheme

@Composable
fun HorizontalDivider() {
    Divider(thickness = 1.dp, color = AppTheme.AppColors.divider)
}