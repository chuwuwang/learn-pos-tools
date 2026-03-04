package com.sea.pos.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme

@Composable
fun RwInputField(height: Dp, value: String, maxLength: Int, onValueChange: (String) -> Unit) {
    Row(modifier = UiUtils.spaceModifier().height(height), verticalAlignment = Alignment.CenterVertically) {
        InputText(Modifier.weight(1.0f).fillMaxHeight(), value, onValueChange)
        LengthText(value.length, maxLength)
    }
}

@Composable
private fun InputText(modifier: Modifier, value: String, onValueChange: (String) -> Unit) {
    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = AppTheme.colors.textMain,
        cursorColor = AppTheme.colors.textChecked,
        unfocusedBorderColor = AppTheme.colors.divider,
        focusedBorderColor = AppTheme.colors.textChecked,
    )
    val textStyle = TextStyle(fontFamily = Fonts.medium, fontSize = Dimens.sp_text, color = AppTheme.colors.textMain, letterSpacing = 3.sp)
    OutlinedTextField(modifier = modifier, value = value, onValueChange = onValueChange, textStyle = textStyle, colors = textFieldColors)
}

@Composable
private fun LengthText(length: Int, maxLength: Int) {
    val color = if (length > maxLength) AppTheme.colors.textError else AppTheme.colors.textChecked
    Text(modifier = Modifier.width(Dimens.item_norm), text = "[$length]", color = color, textAlign = TextAlign.Center, fontFamily = Fonts.medium, fontSize = Dimens.sp_text)
}