package com.pos.encode.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sea.pos.theme.AppTheme
import com.sea.pos.resource.Dimens
import com.sea.pos.resource.boldFontFamily
import com.sea.pos.resource.mediumFontFamily

object TextFieldHelper {

    val HINT_TEXT_WIDTH = 56.dp

    @Composable
    fun inputTextField(modifier: Modifier, text: String, value: String, maxLength: Int, onValueChange: (String) -> Unit) {
        Column(modifier) {
            Text(
                modifier = Modifier.padding(Dimens.marginStart, 0.dp, 0.dp, 0.dp),
                text = text,
                fontSize = Dimens.contentSize,
                fontFamily = boldFontFamily,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.contentText
            )
            Row(modifier = Modifier.padding(Dimens.marginStart, 0.dp, 0.dp, 0.dp), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    modifier = Modifier.weight(1.0f).fillMaxHeight(),
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(color = AppTheme.colors.contentText, fontSize = Dimens.contentSize, fontFamily = mediumFontFamily, letterSpacing = Dimens.wordLetterSpacing),
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AppTheme.colors.contentText, cursorColor = AppTheme.colors.borderChecked, unfocusedBorderColor = AppTheme.colors.border, focusedBorderColor = AppTheme.colors.borderChecked)
                )
                Text(
                    modifier = Modifier.width(HINT_TEXT_WIDTH),
                    fontSize = Dimens.contentSize,
                    textAlign = TextAlign.Center,
                    fontFamily = mediumFontFamily,
                    text = "[" + value.length.toString() + "]",
                    color = if (value.length > maxLength) AppTheme.colors.borderError else AppTheme.colors.borderChecked
                )
            }
        }
    }

}