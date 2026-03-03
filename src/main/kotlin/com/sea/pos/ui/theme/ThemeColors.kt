package com.sea.pos.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
class ThemeColors(
    val textMain: Color,
    val textSecondary: Color,
    val textNeutral: Color,
    val textTertiary: Color,
    val textError: Color,

    val mainTheme: Color,

    val bgSidebar: Color,
    val bgContent: Color,

    val icon: Color,
    val iconChecked: Color,

    val divider: Color,

    ///
    val topBarText: Color,
    val topBarTextChecked: Color,
    val topBarDivider: Color,
    val topBarDividerChecked: Color,
    val contentText: Color,
    val contentBackground: Color,
    val border: Color,
    val borderError: Color,
    val borderChecked: Color,
    val dialogText: Color,
    val dialogBackground: Color,
    val button: Color,
)