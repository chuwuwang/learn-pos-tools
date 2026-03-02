package com.pos.encode.com.pos.encode.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pos.encode.com.pos.encode.ui.theme.MainBoldTextStyle
import com.pos.encode.com.pos.encode.ui.theme.MainMediumTextStyle
import com.pos.encode.ui.common.Vertical
import com.pos.encode.ui.theme.Dimens
import com.pos.encode.ui.theme.POSTheme
import com.pos.encode.ui.theme.mediumFontFamily

internal object RwInput {

    @Composable
    fun InputField(lead: String, height: Dp, value: String, maxLength: Int, onValueChange: (String) -> Unit) {
        val modifier = Modifier.height(height)
        Column(modifier) {

            LeadText(lead)

            Vertical(Dimens.space_norm)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Input(Modifier.weight(1.0f).fillMaxHeight(), value, onValueChange)
                LengthText(value, maxLength)
            }

        }
    }

    @Composable
    private fun Input(modifier: Modifier, value: String, onValueChange: (String) -> Unit) {
        val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = POSTheme.colors.contentText,
            cursorColor = POSTheme.colors.borderChecked,
            unfocusedBorderColor = POSTheme.colors.border,
            focusedBorderColor = POSTheme.colors.borderChecked
        )
        val textStyle = MainMediumTextStyle().copy(letterSpacing = Dimens.wordLetterSpacing)
        OutlinedTextField(modifier = modifier, value = value, onValueChange = onValueChange, textStyle = textStyle, colors = textFieldColors)
    }

    @Composable
    private fun LeadText(lead: String) {
        val style = MainBoldTextStyle()
        Text(text = lead, style = style)
    }

    @Composable
    private fun LengthText(value: String, maxLength: Int) {
        val modifier = Modifier.width(56.dp)
        val text = "[" + value.length.toString() + "]"
        val color = if (value.length > maxLength) POSTheme.colors.borderError else POSTheme.colors.borderChecked
        Text(modifier = modifier, text = text, color = color, textAlign = TextAlign.Center, fontFamily = mediumFontFamily, fontSize = Dimens.secondarySize)
    }

}