package com.sea.pos.ui.widget

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme

@Composable
fun RwHintText(text: String) {
    Text(modifier = UiUtils.spaceXxxModifier, text = text, fontSize = Dimens.sp_text, fontFamily = Fonts.bold, textAlign = TextAlign.Center, color = AppTheme.AppColors.textMain)
}