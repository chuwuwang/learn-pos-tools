package com.sea.pos.ui.widget

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
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme

private val width = 96.dp
private val height = 80.dp

@Composable
fun RwDecryptButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    val buttonColors = ButtonDefaults.buttonColors(AppTheme.colors.button)
    Button(modifier = modifier.size(width, height), colors = buttonColors, onClick = onClick) {
        val painter = painterResource("images/ic_decrypt.png")
        Icon(painter = painter, modifier = Modifier.size(32.dp), tint = AppTheme.colors.icon, contentDescription = null)
    }
}

@Composable
fun RwTextButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    val buttonColors = ButtonDefaults.buttonColors(AppTheme.colors.button)
    Button(modifier = modifier.size(width, height), colors = buttonColors, onClick = onClick) {
        val textStyle = TextStyle(color = AppTheme.colors.buttonText, fontFamily = Fonts.bold, fontSize = Dimens.sp_title, textAlign = TextAlign.Center)
        Text(text, style = textStyle)
    }
}
