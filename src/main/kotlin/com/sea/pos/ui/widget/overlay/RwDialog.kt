package com.sea.pos.ui.widget.overlay

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
    BasicAlertDialog(onDismissRequest = dialog.onConfirm) {

        Surface(shape = UiUtils.roundedCornerShape_16) {

            Column {

                Vertical(32.dp)

                val painter = painterResource("images/ic_dialog_error.png")
                Icon(painter = painter, modifier = Modifier.size(32.dp), contentDescription = null)

                Vertical(16.dp)

                if (dialog.title.valid) {
                    Text(color = AppTheme.AppColors.textMain, textAlign = TextAlign.Start, fontSize = Dimens.sp_title, fontFamily = Fonts.bold, text = dialog.title)
                    Vertical(8.dp)
                }

                Text(color = AppTheme.AppColors.textSecondary, textAlign = TextAlign.Start, fontSize = Dimens.sp_title, fontFamily = Fonts.medium, text = dialog.message)


                Vertical(24.dp)


                Button(modifier = Modifier.fillMaxWidth(), colors = RwButton.ButtonCheckedColors, onClick = dialog.onConfirm) {
                    Text(dialog.confirmText, style = RwButton.ButtonCheckedTextStyle)
                }

                Vertical(32.dp)

            }

        }

    }

}

//Button(
//onClick = dialog.onDismiss,
//modifier = Modifier.weight(1f),
//border = BorderStroke(2.dp, AppTheme.AppColors.divider),
//colors = ButtonDefaults.buttonColors(contentColor = AppTheme.AppColors.button)
//) {
//    Text(dialog.dismissText, style = RwButton.ButtonTextStyle)
//}