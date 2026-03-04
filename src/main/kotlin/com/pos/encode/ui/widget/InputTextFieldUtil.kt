package com.pos.encode.com.pos.encode.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.mediumFontFamily

object InputTextFieldUtil {

    val TEXT_LENGTH_WIDTH = 60.dp

    @Composable
    fun showInputTextFieldWithLength(modifier: Modifier, value: String, maxLength: Int = Int.MAX_VALUE, onValueChange: (String) -> Unit) {
        val textStyle = TextStyle(color = AppTheme.AppColors.contentText, fontSize = Dimens.contentSize, fontFamily = mediumFontFamily, letterSpacing = Dimens.wordLetterSpacing)
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(modifier = Modifier.weight(1.0f).fillMaxHeight(), value = value, onValueChange = onValueChange, textStyle = textStyle, colors = BackgroundUtil.inputTextFieldColor)
            Text(modifier = Modifier.width(TEXT_LENGTH_WIDTH), fontSize = Dimens.contentSize, textAlign = TextAlign.Center, fontFamily = mediumFontFamily, text = "[" + value.length.toString() + "]", color = if (value.length > maxLength) AppTheme.AppColors.borderError else AppTheme.AppColors.borderChecked)
        }
    }

}