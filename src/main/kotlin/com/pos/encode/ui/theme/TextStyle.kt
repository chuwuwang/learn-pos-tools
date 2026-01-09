package com.pos.encode.com.pos.encode.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.pos.encode.ui.theme.DP
import com.pos.encode.ui.theme.POSTheme
import com.pos.encode.ui.theme.boldFontFamily
import com.pos.encode.ui.theme.mediumFontFamily

@Composable
fun MainBoldTextStyle(): TextStyle {
    return TextStyle(fontFamily = boldFontFamily, fontSize = DP.mainSize, color = POSTheme.colors.textMain)
}

@Composable
fun MainMediumTextStyle(): TextStyle {
    return TextStyle(fontFamily = mediumFontFamily, fontSize = DP.mainSize, color = POSTheme.colors.textMain)
}