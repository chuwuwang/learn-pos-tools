package com.pos.encode.com.pos.encode.ui.common

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
import com.pos.encode.theme.AppTheme
import com.pos.encode.ui.theme.Colors
import com.pos.encode.ui.theme.Dimens
import com.pos.encode.ui.theme.boldFontFamily

internal object RwButton {

    private val buttonWidth = 96.dp
    private val buttonHeight = 72.dp

    @Composable
    fun DecryptButton(onClick: () -> Unit) {
        val modifier = Modifier.size(buttonWidth, buttonHeight)
        val buttonColors = ButtonDefaults.buttonColors(AppTheme.colors.mainTheme)
        Button(modifier = modifier, colors = buttonColors, onClick = onClick) {
            val painter = painterResource("images/ic_decrypt.png")
            Icon(painter = painter, modifier = Modifier.size(36.dp), tint = AppTheme.colors.icon, contentDescription = null)
        }
    }

    @Composable
    fun ErrorButton(text: String, onClick: () -> Unit) {
        val modifier = Modifier.size(buttonWidth, buttonHeight)
        val buttonColors = ButtonDefaults.buttonColors(AppTheme.colors.textError)
        Button(modifier = modifier, colors = buttonColors, onClick = onClick) {
            val textStyle = TextStyle(
                color = Colors.white, fontFamily = boldFontFamily, fontSize = Dimens.titleSize, textAlign = TextAlign.Center
            )
            Text(text, style = textStyle)
        }
    }

}