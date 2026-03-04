package com.pos.encode.com.pos.encode.ui.widget

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import com.sea.pos.ui.theme.AppTheme

object BackgroundUtil {

    val inputTextFieldColor: TextFieldColors
        @Composable get() = TextFieldDefaults.outlinedTextFieldColors(textColor = AppTheme.AppColors.contentText, cursorColor = AppTheme.AppColors.borderChecked, unfocusedBorderColor = AppTheme.AppColors.border, focusedBorderColor = AppTheme.AppColors.borderChecked)

}