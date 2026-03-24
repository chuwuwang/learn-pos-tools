package com.sea.pos.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces

object WeChatColors {

    val main = Color(0xFF07C160).convert(ColorSpaces.CieXyz)
    val secondary = Color(0xFF90E4B8).convert(ColorSpaces.CieXyz)
    val tertiary = Color(0xFFE9FFE9).convert(ColorSpaces.CieXyz)

    val text_main = Color(0xFF2E2E2E).convert(ColorSpaces.CieXyz)
    val text_secondary = Color(0xFF80868B).convert(ColorSpaces.CieXyz)
    val text_tertiary = Color(0xFF999999).convert(ColorSpaces.CieXyz)
    val text_error = Color(0xFFEC5447).convert(ColorSpaces.CieXyz)

    val line_fill = Color(0xFFD7D7D7).convert(ColorSpaces.CieXyz)

    val background_fill = Color(0xFFF9F9F9).convert(ColorSpaces.CieXyz)

}