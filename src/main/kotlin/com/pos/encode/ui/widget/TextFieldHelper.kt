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
import com.pos.encode.ui.theme.DP
import com.pos.encode.ui.theme.POSTheme
import com.pos.encode.ui.theme.boldFontFamily
import com.pos.encode.ui.theme.mediumFontFamily

object TextFieldHelper {

    val HINT_TEXT_WIDTH = 56.dp

    @Composable
    fun inputTextField(modifier: Modifier, text: String, value: String, maxLength: Int, onValueChange: (String) -> Unit) {
        Column(modifier) {
            Text(
                modifier = Modifier.padding(DP.marginStart, 0.dp, 0.dp, 0.dp),
                text = text,
                fontSize = DP.contentSize,
                fontFamily = boldFontFamily,
                textAlign = TextAlign.Center,
                color = POSTheme.colors.contentText
            )
            Row(modifier = Modifier.padding(DP.marginStart, 0.dp, 0.dp, 0.dp), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    modifier = Modifier.weight(1.0f).fillMaxHeight(),
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(color = POSTheme.colors.contentText, fontSize = DP.contentSize, fontFamily = mediumFontFamily, letterSpacing = DP.wordLetterSpacing),
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = POSTheme.colors.contentText, cursorColor = POSTheme.colors.borderChecked, unfocusedBorderColor = POSTheme.colors.border, focusedBorderColor = POSTheme.colors.borderChecked)
                )
                Text(
                    modifier = Modifier.width(HINT_TEXT_WIDTH),
                    fontSize = DP.contentSize,
                    textAlign = TextAlign.Center,
                    fontFamily = mediumFontFamily,
                    text = "[" + value.length.toString() + "]",
                    color = if (value.length > maxLength) POSTheme.colors.borderError else POSTheme.colors.borderChecked
                )
            }
        }
    }

}