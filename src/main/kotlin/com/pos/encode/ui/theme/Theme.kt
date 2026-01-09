package com.pos.encode.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Stable
object POSTheme {

    enum class Theme {
        Light, Dark, WeChat
    }

    val colors: POSColors
        @Composable get() = DefaultLocalColors.current

}

private val DefaultLocalColors = staticCompositionLocalOf { WeChat }

@Stable
class POSColors(
    val sidebar: Color,
    val sidebarBackground: Color,

    val topBar: Color,
    val topBarBackground: Color,
    val topBarText: Color,
    val topBarTextChecked: Color,
    val topBarDivider: Color,
    val topBarDividerChecked: Color,

    val icon: Color,
    val iconChecked: Color,

    val contentText: Color,
    val contentBackground: Color,

    val textMain: Color,
    val textSecondary: Color,

    val border: Color,
    val borderError: Color,
    val borderChecked: Color,
    val dialogText: Color,
    val dialogBackground: Color,
    val button: Color,
    val divider: Color,



)

private val WeChat = POSColors(
    sidebar = WeColor.green,
    sidebarBackground = WeColor.black,
    topBar = WeColor.green,
    topBarBackground = whiteColor,
    topBarText = WeColor.black,
    topBarTextChecked = WeColor.green,
    topBarDivider = transparentColor,
    topBarDividerChecked = WeColor.green,
    icon = whiteColor,
    iconChecked = WeColor.green,

    contentText = WeColor.black,
    contentBackground = whiteColor,

    textMain = text_main,
    textSecondary = text_secondary,

    border = WeColor.gray,
    borderError = WeColor.danger,
    borderChecked = WeColor.success,
    dialogText = whiteColor,
    dialogBackground = whiteColor,
    button = WeColor.green,
    divider = WeColor.gray,
)

@Suppress("DuplicatedCode")
@Composable
fun seaTheme(theme: POSTheme.Theme = POSTheme.Theme.WeChat, content: @Composable () -> Unit) {
    val targetColors = when (theme) {
        POSTheme.Theme.WeChat -> WeChat
        else -> WeChat
    }
    val animationSpec = TweenSpec<Color>(durationMillis = 600)
    val sidebar = animateColorAsState(targetColors.sidebar, animationSpec)
    val sidebarBackground = animateColorAsState(targetColors.sidebarBackground, animationSpec)
    val topBar = animateColorAsState(targetColors.topBar, animationSpec)
    val topBarBackground = animateColorAsState(targetColors.topBarBackground, animationSpec)
    val topBarText = animateColorAsState(targetColors.topBarText, animationSpec)
    val topBarTextChecked = animateColorAsState(targetColors.topBarTextChecked, animationSpec)
    val topBarDivider = animateColorAsState(targetColors.topBarDivider, animationSpec)
    val topBarDividerChecked = animateColorAsState(targetColors.topBarDividerChecked, animationSpec)
    val icon = animateColorAsState(targetColors.icon, animationSpec)
    val iconChecked = animateColorAsState(targetColors.iconChecked, animationSpec)

    val contentText = animateColorAsState(targetColors.contentText, animationSpec)
    val contentBackground = animateColorAsState(targetColors.contentBackground, animationSpec)
    val textMain = animateColorAsState(targetColors.textMain, animationSpec)
    val textSecondary = animateColorAsState(targetColors.textSecondary, animationSpec)

    val border = animateColorAsState(targetColors.border, animationSpec)
    val borderError = animateColorAsState(targetColors.borderError, animationSpec)
    val borderChecked = animateColorAsState(targetColors.borderChecked, animationSpec)
    val dialogText = animateColorAsState(targetColors.dialogText, animationSpec)
    val dialogBackground = animateColorAsState(targetColors.dialogBackground, animationSpec)
    val button = animateColorAsState(targetColors.button, animationSpec)
    val divider = animateColorAsState(targetColors.divider, animationSpec)
    val colors = POSColors(
        sidebar = sidebar.value,
        sidebarBackground = sidebarBackground.value,
        topBar = topBar.value,
        topBarBackground = topBarBackground.value,
        topBarText = topBarText.value,
        topBarTextChecked = topBarTextChecked.value,
        topBarDivider = topBarDivider.value,
        topBarDividerChecked = topBarDividerChecked.value,
        icon = icon.value,
        iconChecked = iconChecked.value,

        contentText = contentText.value,
        contentBackground = contentBackground.value,

        textMain = textMain.value,
        textSecondary = textSecondary.value,

        border = border.value,
        borderError = borderError.value,
        borderChecked = borderChecked.value,
        dialogText = dialogText.value,
        dialogBackground = dialogBackground.value,
        button = button.value,
        divider = divider.value,
    )
    // Use the font family to define a custom typography
    val craneTypography = Typography(
        defaultFontFamily = POSFontFamily,
        /* ... */
    )
    CompositionLocalProvider(DefaultLocalColors provides colors) {
        MaterialTheme(typography = craneTypography, content = content)
    }
}