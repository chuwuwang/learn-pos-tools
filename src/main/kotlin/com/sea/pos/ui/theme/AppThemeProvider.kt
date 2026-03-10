package com.sea.pos.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import com.sea.pos.ui.resource.Fonts

@Composable
fun SeaTheme(theme: AppTheme.Theme = AppTheme.Theme.WeChat, content: @Composable () -> Unit) {
    val targetColors = when (theme) {
        AppTheme.Theme.WeChat -> AppTheme.wechatColors
        else -> AppTheme.wechatColors
    }
    val bgContent = AnimateColorAsState(targetColors.bgContent)
    val bgSidebar = AnimateColorAsState(targetColors.bgSidebar)

    val textMain = AnimateColorAsState(targetColors.textMain)
    val textSecondary = AnimateColorAsState(targetColors.textSecondary)
    val textTertiary = AnimateColorAsState(targetColors.textTertiary)
    val textError = AnimateColorAsState(targetColors.textError)
    val textChecked = AnimateColorAsState(targetColors.textChecked)

    val icon = AnimateColorAsState(targetColors.icon)
    val iconChecked = AnimateColorAsState(targetColors.iconChecked)

    val button = AnimateColorAsState(targetColors.button)
    val buttonText = AnimateColorAsState(targetColors.buttonText)
    val buttonChecked = AnimateColorAsState(targetColors.buttonChecked)
    val buttonCheckedText = AnimateColorAsState(targetColors.buttonCheckedText)

    val divider = AnimateColorAsState(targetColors.divider)
    val dividerError = AnimateColorAsState(targetColors.dividerError)
    val dividerChecked = AnimateColorAsState(targetColors.dividerChecked)

    val colors = ThemeColors(
        bgContent = bgContent.value,
        bgSidebar = bgSidebar.value,

        textMain = textMain.value,
        textSecondary = textSecondary.value,
        textTertiary = textTertiary.value,
        textError = textError.value,
        textChecked = textChecked.value,

        icon = icon.value,
        iconChecked = iconChecked.value,

        button = button.value,
        buttonText = buttonText.value,
        buttonChecked = buttonChecked.value,
        buttonCheckedText = buttonCheckedText.value,

        divider = divider.value,
        dividerError = dividerError.value,
        dividerChecked = dividerChecked.value,
    )

    // Use the font family to define a custom typography
    val craneTypography = Typography(defaultFontFamily = Fonts.defaultFontFamily)
    CompositionLocalProvider(AppTheme.defaultLocalColors provides colors) {
        MaterialTheme(typography = craneTypography, content = content)
    }
}

@Composable
private fun AnimateColorAsState(targetValue: Color): State<Color> {
    val animationSpec = TweenSpec<Color>(durationMillis = 600)
    return animateColorAsState(targetValue, animationSpec)
}