package com.pos.encode.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.pos.encode.ui.theme.*

object DialogHelper {

    @Suppress("DEPRECATION")
    @Composable
    fun errorDialog(message: String, visible: MutableState<Boolean>) {
        Dialog(onCloseRequest = { visible.value = false },
            visible = visible.value,
            resizable = false,
            title = Strings.error.toUpperCase(),
            content = {
                val modifier = Modifier.fillMaxSize().background(POSTheme.colors.dialogBackground)
                Column(modifier) {
                    Text(
                        modifier = Modifier.padding(Dimens.dialogPadding, Dimens.dialogPadding, Dimens.dialogPadding, 0.dp),
                        textAlign = TextAlign.Start,
                        fontSize = Dimens.contentSize,
                        color = POSTheme.colors.contentText,
                        text = message,
                        fontFamily = mediumFontFamily
                    )
                    Column(
                        modifier = Modifier.fillMaxSize().padding(Dimens.dialogPadding),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Column(
                            modifier = Modifier.size(Dimens.dialogButtonWidth, Dimens.dialogButtonHeight).background(shape = RoundedCornerShape(Dimens.radius), color = POSTheme.colors.button).clickable { visible.value = false },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                textAlign = TextAlign.Center,
                                fontSize = Dimens.contentSize,
                                color = POSTheme.colors.dialogText,
                                text = Strings.ok.uppercase(),
                                fontFamily = boldFontFamily
                            )
                        }
                    }
                }
            }
        )
    }

}