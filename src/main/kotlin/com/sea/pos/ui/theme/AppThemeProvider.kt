package com.sea.pos.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.sea.pos.ui.resource.Fonts

@Composable
fun SeaTheme(theme: AppTheme.Theme = AppTheme.Theme.WeChat, content: @Composable () -> Unit) {
    val targetColors = when (theme) {
        AppTheme.Theme.WeChat -> AppTheme.wechatColors
        else -> AppTheme.wechatColors
    }
    val animationSpec = TweenSpec<Color>(durationMillis = 600)
    val textMain = animateColorAsState(targetColors.textMain, animationSpec)
    val textSecondary = animateColorAsState(targetColors.textSecondary, animationSpec)
    val textTertiary = animateColorAsState(targetColors.textTertiary, animationSpec)
    val textError = animateColorAsState(targetColors.textError, animationSpec)
    val textChecked = animateColorAsState(targetColors.textChecked, animationSpec)

    val mainTheme = animateColorAsState(targetColors.mainTheme, animationSpec)

    val bgSidebar = animateColorAsState(targetColors.bgSidebar, animationSpec)
    val bgContent = animateColorAsState(targetColors.bgContent, animationSpec)

    val icon = animateColorAsState(targetColors.icon, animationSpec)
    val iconChecked = animateColorAsState(targetColors.iconChecked, animationSpec)
    val button = animateColorAsState(targetColors.button, animationSpec)
    val buttonText = animateColorAsState(targetColors.buttonText, animationSpec)
    val buttonChecked = animateColorAsState(targetColors.buttonChecked, animationSpec)
    val buttonCheckedText = animateColorAsState(targetColors.buttonCheckedText, animationSpec)
    val divider = animateColorAsState(targetColors.divider, animationSpec)
    val dividerChecked = animateColorAsState(targetColors.dividerChecked, animationSpec)

    val topBarText = animateColorAsState(targetColors.topBarText, animationSpec)
    val topBarTextChecked = animateColorAsState(targetColors.topBarTextChecked, animationSpec)
    val topBarDivider = animateColorAsState(targetColors.topBarDivider, animationSpec)
    val topBarDividerChecked = animateColorAsState(targetColors.topBarDividerChecked, animationSpec)


    val contentText = animateColorAsState(targetColors.contentText, animationSpec)
    val contentBackground = animateColorAsState(targetColors.contentBackground, animationSpec)


    val border = animateColorAsState(targetColors.border, animationSpec)
    val borderError = animateColorAsState(targetColors.borderError, animationSpec)
    val borderChecked = animateColorAsState(targetColors.borderChecked, animationSpec)
    val dialogText = animateColorAsState(targetColors.dialogText, animationSpec)
    val dialogBackground = animateColorAsState(targetColors.dialogBackground, animationSpec)


    val colors = ThemeColors(
        textMain = textMain.value,
        textSecondary = textSecondary.value,
        textTertiary = textTertiary.value,
        textError = textError.value,
        textChecked = textChecked.value,

        mainTheme = mainTheme.value,

        bgSidebar = bgSidebar.value,
        bgContent = bgContent.value,

        icon = icon.value,
        iconChecked = iconChecked.value,
        button = button.value,
        buttonText = buttonText.value,
        buttonChecked = buttonChecked.value,
        buttonCheckedText = buttonCheckedText.value,
        divider = divider.value,
        dividerChecked = dividerChecked.value,

        /////////////

        topBarText = topBarText.value,
        topBarTextChecked = topBarTextChecked.value,
        topBarDivider = topBarDivider.value,
        topBarDividerChecked = topBarDividerChecked.value,


        contentText = contentText.value,
        contentBackground = contentBackground.value,


        border = border.value,
        borderError = borderError.value,
        borderChecked = borderChecked.value,
        dialogText = dialogText.value,
        dialogBackground = dialogBackground.value,
    )

    // Use the font family to define a custom typography
    val craneTypography = Typography(
        defaultFontFamily = Fonts.defaultFontFamily,
        /* ... */
    )
    CompositionLocalProvider(AppTheme.defaultLocalColors provides colors) {
        MaterialTheme(typography = craneTypography, content = content)
    }
}