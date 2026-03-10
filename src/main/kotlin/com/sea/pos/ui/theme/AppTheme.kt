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

    val AppColors: ThemeColors
        @Composable get() = defaultLocalColors.current

    val defaultLocalColors = staticCompositionLocalOf { wechatColors }

    val wechatColors = ThemeColors(
        bgContent = Colors.white,
        bgSidebar = WeChatColors.black,

        textMain = WeChatColors.black,
        textTertiary = Colors.text_tertiary,
        textSecondary = Colors.text_secondary,
        textError = Colors.text_error,
        textChecked = WeChatColors.main_theme,

        icon = Colors.white,
        iconChecked = WeChatColors.main_theme,

        button = Colors.white,
        buttonText = WeChatColors.black,
        buttonChecked = WeChatColors.main_theme,
        buttonCheckedText = Colors.white,

        divider = WeChatColors.gray,
        dividerError = Colors.text_error,
        dividerChecked = WeChatColors.main_theme,
    )

}