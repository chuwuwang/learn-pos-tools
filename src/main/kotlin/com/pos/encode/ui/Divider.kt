package com.pos.encode.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sea.pos.resource.Colors

@Composable
fun dividerLightGray() {
    Divider(
        Modifier.fillMaxWidth().height(1.dp),
        color = Colors.line_fill,
    )
}

@Composable
fun dividerVerticalLightGray() {
    Divider(
        Modifier.fillMaxHeight().width(1.dp),
        color = Colors.line_fill,
    )
}

