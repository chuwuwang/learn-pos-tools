package com.pos.encode.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces

object WeChatColors {

    val main_theme = Color(0xFF1AB84E).convert(ColorSpaces.CieXyz)

    val gray = Color(0xFFD7D7D7).convert(ColorSpaces.CieXyz)
    val black = Color(0xFF2E2E2E).convert(ColorSpaces.CieXyz)

    val info = Color(0xFF909399).convert(ColorSpaces.CieXyz)
    val danger = Color(0xFFF56C6C).convert(ColorSpaces.CieXyz)
    val success = Color(0xFF67C23A).convert(ColorSpaces.CieXyz)
    val warning = Color(0xFFE6A23C).convert(ColorSpaces.CieXyz)

}