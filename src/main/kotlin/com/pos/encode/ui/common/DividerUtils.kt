package com.pos.encode.ui.common

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.pos.encode.theme.AppTheme

@Composable
fun HorizontalDivider() {
    Divider(thickness = 1.dp, color = AppTheme.colors.divider)
}