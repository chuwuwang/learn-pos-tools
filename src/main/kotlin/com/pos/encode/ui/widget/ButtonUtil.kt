package com.pos.encode.com.pos.encode.ui.widget

import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.resource.Colors
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.boldFontFamily

object ButtonUtil {

    @Composable
    fun encryptButton(modifier: Modifier, onClick: () -> Unit) {
        Button(modifier = modifier.size(Dimens.buttonWidth, Dimens.buttonHeight), colors = ButtonDefaults.buttonColors(
            AppTheme.AppColors.button), onClick = onClick) {
            Icon(painter = painterResource("images/ic_encrypt.png"), modifier = Modifier.size(36.dp), tint = AppTheme.AppColors.icon, contentDescription = null)
        }
    }





}

@Composable
fun decryptButton(onClick: () -> Unit) {
    Button(modifier = Modifier.size(Dimens.buttonWidth, Dimens.buttonHeight), colors = ButtonDefaults.buttonColors(AppTheme.AppColors.button), onClick = onClick) {
        Icon(painter = painterResource("images/ic_decrypt.png"), modifier = Modifier.size(36.dp), tint = AppTheme.AppColors.icon, contentDescription = null)
    }
}

@Composable
fun errorButton(text: String, onClick: () -> Unit) {
    Button(modifier = Modifier.size(Dimens.buttonWidth, Dimens.buttonHeight), colors = ButtonDefaults.buttonColors(
        Colors. text_error), onClick = onClick) {
        val textStyle = TextStyle(color = Colors.white, fontFamily = boldFontFamily, fontSize = Dimens.titleSize, textAlign = TextAlign.Center)
        Text(text, style = textStyle)
    }
}