package com.sea.pos.ui.widget

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme

private val width = 96.dp
private val height = 80.dp

@Composable
fun RwDecryptButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(modifier = modifier.size(width, height), colors = RwButton.ButtonCheckedColors, onClick = onClick) {
        val painter = painterResource("images/ic_decrypt.png")
        Icon(painter = painter, modifier = Modifier.size(32.dp), tint = AppTheme.AppColors.icon, contentDescription = null)
    }
}

@Composable
fun RwTextButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(modifier = modifier.size(width, height), colors = RwButton.ButtonCheckedColors, onClick = onClick) {
        Text(text, style = RwButton.ButtonTextStyle)
    }
}

@Composable
fun RwErrorButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(modifier = modifier.size(width, height), colors = RwButton.ErrorButtonColors, onClick = onClick) {
        Text(text, style = RwButton.ButtonCheckedTextStyle)
    }
}

object RwButton {

    val ButtonColors: ButtonColors
        @Composable get() = ButtonDefaults.buttonColors(AppTheme.AppColors.button)
    val ButtonTextStyle: TextStyle
        @Composable get() = TextStyle(color = AppTheme.AppColors.buttonText, fontFamily = Fonts.bold, fontSize = Dimens.sp_title, textAlign = TextAlign.Center, letterSpacing = Dimens.sp_letter)

    val ButtonCheckedColors: ButtonColors
        @Composable get() = ButtonDefaults.buttonColors(AppTheme.AppColors.buttonChecked)
    val ButtonCheckedTextStyle: TextStyle
        @Composable get() = TextStyle(color = AppTheme.AppColors.buttonCheckedText, fontFamily = Fonts.bold, fontSize = Dimens.sp_title, textAlign = TextAlign.Center, letterSpacing = Dimens.sp_letter)

    val ErrorButtonColors: ButtonColors
        @Composable get() = ButtonDefaults.buttonColors(AppTheme.AppColors.textError)

}