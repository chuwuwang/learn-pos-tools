package com.pos.encode.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.resource.Colors
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.boldFontFamily
import com.sea.pos.ui.resource.mediumFontFamily

object ButtonHelper {

    val RADIO_GROUP_HEIGHT = 72.dp
    val TEXT_MARGIN_BORDER = 10.dp

    @Composable
    fun encryptButton(onClick: () -> Unit) {
        Button(
            modifier = Modifier.size(Dimens.buttonWidth, Dimens.buttonHeight).padding(0.dp, 0.dp, Dimens.marginEnd, 0.dp),
            colors = ButtonDefaults.buttonColors(AppTheme.AppColors.button),
            onClick = onClick
        ) {
            Icon(
                painter = painterResource("images/ic_encrypt.png"),
                modifier = Modifier.size(36.dp),
                tint = AppTheme.AppColors.icon,
                contentDescription = null
            )
        }
    }

    @Composable
    fun decryptButton(onClick: () -> Unit) {
        Button(
            modifier = Modifier.size(Dimens.buttonWidth, Dimens.buttonHeight).padding(0.dp, 0.dp, Dimens.marginEnd, 0.dp),
            colors = ButtonDefaults.buttonColors(AppTheme.AppColors.button),
            onClick = onClick
        ) {
            Icon(
                painter = painterResource("images/ic_decrypt.png"),
                modifier = Modifier.size(36.dp),
                tint = AppTheme.AppColors.icon,
                contentDescription = null
            )
        }
    }

    @Composable
    fun radioButton(text: String, selected: Boolean, onClick: () -> Unit) {
        Row(modifier = Modifier.width(192.dp).fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(onClick = onClick, selected = selected, colors = RadioButtonDefaults.colors(AppTheme.AppColors.button))
            Text(text = text, fontSize = Dimens.contentSize, fontFamily = mediumFontFamily, color = AppTheme.AppColors.contentText)
        }
    }

    @Composable
    fun radioGroup(modifier: Modifier, text: String, height: Dp = RADIO_GROUP_HEIGHT, content: @Composable () -> Unit) {
        val borderWidth = Dimens.borderWidth
        val borderColor = AppTheme.AppColors.borderChecked
        Box(modifier = modifier.height(height), contentAlignment = Alignment.Center) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Divider(modifier = Modifier.width(24.dp).height(borderWidth), color = borderColor)
                    Text(modifier = Modifier.padding(4.dp, 0.dp), fontSize = Dimens.contentSize, color = AppTheme.AppColors.contentText, text = text, fontFamily = boldFontFamily)
                    Divider(modifier = Modifier.fillMaxWidth().height(borderWidth), color = borderColor)
                }
                val params = Modifier.weight(1.0f)
                Spacer(params)
                Divider(modifier = Modifier.fillMaxWidth().height(borderWidth), color = borderColor)
            }
            Row(modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp), horizontalArrangement = Arrangement.Start) {
                Divider(modifier = Modifier.width(borderWidth).fillMaxHeight(), color = borderColor)
                val params = Modifier.weight(1.0f)
                Spacer(params)
                Divider(modifier = Modifier.width(borderWidth).fillMaxHeight(), color = borderColor)
            }
            content()
        }
    }

}

@Composable
fun posButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    elevation: Dp = 0.dp,
    cornerRadius: Dp = 0.dp,
    backgroundColor: Color = Colors.white,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    border: BorderStroke ? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = ButtonDefaults.elevation(elevation),
        shape = RoundedCornerShape(cornerRadius),
        border = border,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun whiteRectangleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    border: BorderStroke ? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(0.dp),
        border = border,
        colors = ButtonDefaults.buttonColors(Colors.white),
        contentPadding = contentPadding,
        content = content
    )
}