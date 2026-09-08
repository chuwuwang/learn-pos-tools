package com.sea.pos.ui.widget.overlay

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sea.pos.extension.valid
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.widget.RwButton
import com.sea.pos.ui.widget.RwVertical
import com.sea.pos.ui.widget.UiUtils

@ExperimentalMaterial3Api
@Composable
fun RwErrorDialog(dialog: AppDialog) {
    val onDismissRequest = { }
    BasicAlertDialog(onDismissRequest = onDismissRequest) {

        Surface(shape = UiUtils.roundedCornerShape_16) {

            Column(modifier = Modifier.padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                val painter = painterResource("images/ic_dialog_error.png")
                Icon(painter = painter, modifier = Modifier.size(48.dp), contentDescription = null, tint = AppTheme.AppColors.textError)

                RwVertical(Dimens.space_xx)

                if (dialog.title.valid) {
                    Text(style = RwDialog.TitleTextStyle, text = dialog.title)
                    RwVertical(Dimens.space_x)
                }

                Text(style = RwDialog.ContentTextStyle, text = dialog.message)

                RwVertical(Dimens.space_xx)

                val onClick = {
                    DialogManager.dismiss()
                    dialog.onConfirm.invoke()
                }
                Button(modifier = UiUtils.modifierSpace_xxx.width(320.dp).height(Dimens.item_lg), colors = RwButton.ButtonCheckedColors, onClick = onClick) {
                    Text(dialog.confirmText, style = RwButton.ButtonCheckedTextStyle)
                }

            }

        }

    }
}

@ExperimentalMaterial3Api
@Composable
fun RwLoadingDialog(dialog: AppDialog) {
    val onDismissRequest = { }
    BasicAlertDialog(onDismissRequest = onDismissRequest) {

        Surface(shape = UiUtils.roundedCornerShape_16) {

            Column(modifier = Modifier.padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                androidx.compose.material3.CircularProgressIndicator(modifier = Modifier.size(48.dp), color = AppTheme.AppColors.buttonChecked)

                RwVertical(Dimens.space_xxx)

                Text(text = dialog.title, style = RwDialog.TitleTextStyle)

                if (dialog.message.valid) {
                    RwVertical(Dimens.space_x)
                    Text(text = dialog.message, style = RwDialog.ContentTextStyle)
                }

            }

        }

    }
}

internal object RwDialog {

    val TitleTextStyle: TextStyle
        @Composable get() = TextStyle(color = AppTheme.AppColors.textMain, fontFamily = Fonts.bold, fontSize = Dimens.sp_title, textAlign = TextAlign.Start)

    val ContentTextStyle: TextStyle
        @Composable get() = TextStyle(color = AppTheme.AppColors.textSecondary, fontFamily = Fonts.medium, fontSize = Dimens.sp_title, textAlign = TextAlign.Start)

}