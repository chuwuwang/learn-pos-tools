package com.sea.pos.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces

object WeChatColors {

    val main_theme = Color(0xFF1AB84E).convert(ColorSpaces.CieXyz)

    val text_main = Color(0xFF2E2E2E).convert(ColorSpaces.CieXyz)
    val text_secondary = Color(0xFF80868B).convert(ColorSpaces.CieXyz)
    val text_tertiary = Color(0xFF999999).convert(ColorSpaces.CieXyz)
    val text_error = Color(0xFFEC5447).convert(ColorSpaces.CieXyz)

    val line_fill = Color(0xFFD7D7D7).convert(ColorSpaces.CieXyz)

    val background_fill = Color(0xFFF5F5F5).convert(ColorSpaces.CieXyz)

}