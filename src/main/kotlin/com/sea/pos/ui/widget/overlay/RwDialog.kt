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
import com.sea.pos.ui.widget.UiUtils
import com.sea.pos.ui.widget.Vertical

@ExperimentalMaterial3Api
@Composable
fun RwErrorDialog(dialog: AppDialog) {
    val onDismiss = {
        DialogManager.dismiss()
        dialog.onConfirm.invoke()
    }
    BasicAlertDialog(onDismissRequest = onDismiss) {

        Surface(shape = UiUtils.roundedCornerShape_16) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Vertical(48.dp)

                val painter = painterResource("images/ic_dialog_error.png")
                Icon(painter = painter, modifier = Modifier.size(60.dp), contentDescription = null, tint = AppTheme.AppColors.textError)

                Vertical(Dimens.space_xxx)

                if (dialog.title.valid) {
                    Text(modifier = Modifier.padding(horizontal = Dimens.space_xxx), style = RwDialog.TitleTextStyle, text = dialog.title)
                    Vertical(Dimens.space_x)
                }

                Text(modifier = Modifier.padding(horizontal = Dimens.space_xxx), style = RwDialog.ContentTextStyle, text = dialog.message)

                Vertical(Dimens.space_xxx)

                Button(modifier = UiUtils.modifierSpace_xxx.fillMaxWidth().height(Dimens.item_lg), colors = RwButton.ButtonCheckedColors, onClick = dialog.onConfirm) {
                    Text(dialog.confirmText, style = RwButton.ButtonCheckedTextStyle)
                }

                Vertical(48.dp)
            }

        }

    }
}

object RwDialog {

    val TitleTextStyle: TextStyle
        @Composable get() = TextStyle(color = AppTheme.AppColors.textMain, fontFamily = Fonts.bold, fontSize = Dimens.sp_title, textAlign = TextAlign.Start)

    val ContentTextStyle: TextStyle
        @Composable get() = TextStyle(color = AppTheme.AppColors.textSecondary, fontFamily = Fonts.medium, fontSize = Dimens.sp_title, textAlign = TextAlign.Start)

}