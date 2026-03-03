package com.pos.encode.com.pos.encode.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.boldFontFamily
import com.sea.pos.ui.resource.mediumFontFamily

@Composable
fun MainBoldTextStyle(): TextStyle {
    return TextStyle(fontFamily = boldFontFamily, fontSize = Dimens.mainSize, color = AppTheme.colors.textMain)
}

@Composable
fun MainMediumTextStyle(): TextStyle {
    return TextStyle(fontFamily = mediumFontFamily, fontSize = Dimens.mainSize, color = AppTheme.colors.textMain)
}