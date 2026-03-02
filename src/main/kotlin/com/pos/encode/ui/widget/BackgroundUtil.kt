package com.pos.encode.com.pos.encode.ui.widget

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import com.pos.encode.theme.AppTheme

object BackgroundUtil {

    val inputTextFieldColor: TextFieldColors
        @Composable get() = TextFieldDefaults.outlinedTextFieldColors(textColor = AppTheme.colors.contentText, cursorColor = AppTheme.colors.borderChecked, unfocusedBorderColor = AppTheme.colors.border, focusedBorderColor = AppTheme.colors.borderChecked)

}