package com.sea.pos.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme

@Composable
fun RwInputTextWithLength(
    modifier: Modifier = Modifier, value: String,
    maxLength: Int = Int.MAX_VALUE, hint: String = "",
    input: Boolean = true, enabled: Boolean = true,
    singleLine: Boolean = false, showLength: Boolean = true, onValueChange: (String) -> Unit,
) {
    Row(modifier = modifier.then(other = UiUtils.modifierSpace), verticalAlignment = Alignment.CenterVertically) {
        InputText(Modifier.weight(1.0f).fillMaxHeight(), value, hint = hint, input = input, enabled = enabled, singleLine = singleLine, onValueChange)
        if (showLength) LengthText(value.length, maxLength)
    }
}

@Composable
private fun InputText(modifier: Modifier, value: String, hint: String, input: Boolean, enabled: Boolean, singleLine: Boolean, onValueChange: (String) -> Unit) {
    val colors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = AppTheme.AppColors.textMain,
        cursorColor = AppTheme.AppColors.textChecked,
        unfocusedBorderColor = AppTheme.AppColors.divider,
        focusedBorderColor = AppTheme.AppColors.textChecked,
    )
    if (enabled) {
        OutlinedTextField(modifier = modifier, value = value, colors = colors, enabled = input, singleLine = singleLine, placeholder = PlaceholderText(hint), onValueChange = onValueChange, textStyle = RwInput.InputTextStyle)
    } else {
        OutlinedTextField(modifier = modifier.background(color = AppTheme.AppColors.textDisabled), value = value, colors = colors, enabled = false, singleLine = singleLine, placeholder = PlaceholderText(hint), onValueChange = onValueChange, textStyle = RwInput.DisabledTextStyle)
    }
}

@Composable
private fun PlaceholderText(text: String): @Composable () -> Unit = {
    Text(text = text, style = RwInput.HintTextStyle)
}

@Composable
private fun LengthText(length: Int, maxLength: Int) {
    val color = if (length > maxLength) AppTheme.AppColors.textError else AppTheme.AppColors.textChecked
    Text(modifier = Modifier.width(Dimens.item_norm), text = "[$length]", color = color, textAlign = TextAlign.Center, fontFamily = Fonts.medium, fontSize = Dimens.sp_text)
}

object RwInput {

    val HintTextStyle: TextStyle
        @Composable get() = TextStyle(color = AppTheme.AppColors.textTertiary, fontFamily = Fonts.regular, fontSize = Dimens.sp_text)

    val InputTextStyle: TextStyle
        @Composable get() = TextStyle(color = AppTheme.AppColors.textMain, fontFamily = Fonts.medium, fontSize = Dimens.sp_text, letterSpacing = Dimens.sp_letter)

    val DisabledTextStyle: TextStyle
        @Composable get() = TextStyle(color = AppTheme.AppColors.textTertiary, fontFamily = Fonts.medium, fontSize = Dimens.sp_text, letterSpacing = Dimens.sp_letter)

}