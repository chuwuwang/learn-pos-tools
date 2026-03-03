package com.pos.encode.com.pos.encode.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.sea.pos.theme.AppTheme
import com.sea.pos.resource.Dimens
import com.sea.pos.resource.boldFontFamily
import com.sea.pos.resource.mediumFontFamily

@Composable
fun MainBoldTextStyle(): TextStyle {
    return TextStyle(fontFamily = boldFontFamily, fontSize = Dimens.mainSize, color = AppTheme.colors.textMain)
}

@Composable
fun MainMediumTextStyle(): TextStyle {
    return TextStyle(fontFamily = mediumFontFamily, fontSize = Dimens.mainSize, color = AppTheme.colors.textMain)
}