package com.sea.pos.ui.util

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sea.pos.theme.AppTheme

@Composable
fun HorizontalDivider() {
    Divider(thickness = 1.dp, color = AppTheme.colors.divider)
}