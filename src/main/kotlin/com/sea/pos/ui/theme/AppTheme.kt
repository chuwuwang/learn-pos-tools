package com.sea.pos.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import com.sea.pos.ui.resource.Colors

@Stable
object AppTheme {

    enum class Theme {

        Light, Dark, WeChat

    }

    val colors: ThemeColors
        @Composable get() = defaultLocalColors.current

    val defaultLocalColors = staticCompositionLocalOf { wechatColors }

    val wechatColors = ThemeColors(
        textMain = WeChatColors.black,
        textNeutral = Colors.text_neutral,
        textTertiary = Colors.text_tertiary,
        textSecondary = Colors.text_secondary,
        textError = Colors.text_error,
        textChecked = WeChatColors.main_theme,

        mainTheme = WeChatColors.main_theme,

        bgSidebar = WeChatColors.black,
        bgContent = Colors.white,

        icon = Colors.white,
        iconChecked = WeChatColors.main_theme,
        button = WeChatColors.main_theme,
        buttonText = Colors.white,
        buttonChecked = WeChatColors.main_theme,
        buttonCheckedText = Colors.white,
        divider = WeChatColors.gray,
        dividerChecked = WeChatColors.main_theme,

        ///

        topBarText = WeChatColors.black,
        topBarTextChecked = WeChatColors.main_theme,
        topBarDivider = Colors.transparent,
        topBarDividerChecked = WeChatColors.main_theme,


        contentText = WeChatColors.black,
        contentBackground = Colors.white,


        border = WeChatColors.gray,
        borderError = WeChatColors.danger,
        borderChecked = WeChatColors.success,
        dialogText = Colors.white,
        dialogBackground = Colors.white,
    )

}