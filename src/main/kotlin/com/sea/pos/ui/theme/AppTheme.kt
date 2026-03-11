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
        bgSidebar = WeChatColors.text_main,

        textMain = WeChatColors.text_main,
        textTertiary = WeChatColors.text_tertiary,
        textSecondary = WeChatColors.text_secondary,
        textError = WeChatColors.text_error,
        textChecked = WeChatColors.main_theme,
        textDisabled = WeChatColors.background_fill,

        icon = Colors.white,
        iconChecked = WeChatColors.main_theme,

        button = Colors.white,
        buttonText = WeChatColors.text_main,
        buttonChecked = WeChatColors.main_theme,
        buttonCheckedText = Colors.white,

        divider = WeChatColors.line_fill,
        dividerError = WeChatColors.text_error,
        dividerChecked = WeChatColors.main_theme,
    )

}