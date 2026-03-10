package com.sea.pos.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.resource.Fonts
import com.sea.pos.ui.theme.AppTheme

private val borderWidth = 2.dp

@Composable
fun RwRadioGroup(list: List<String>, label: String, selected: Int = 0, onClick: (String) -> Unit) {
    val selected = remember {
        val string = list[selected]
        mutableStateOf(string)
    }
    BoxWithConstraints(modifier = UiUtils.modifierSpace_xxx.height(64.dp), contentAlignment = Alignment.Center) {
        // 从上到下绘制 divider
        DividerHorizontal(label)

        // 从左到右绘制 divider
        DividerVertical()

        val content: @Composable RowScope.() -> Unit = {
            for (item in list) {
                RwRadioButton(Modifier.weight(1f), item, item == selected.value) {
                    selected.value = item
                    onClick(item)
                }
            }
        }
        val modifier = Modifier.padding(start = Dimens.space_x, end = Dimens.space_x, top = Dimens.space_norm)
        Row(modifier = modifier, content = content)
    }
}

@Composable
fun RwRadioButton(modifier: Modifier, text: String, selected: Boolean, onClick: () -> Unit) {
    Row(modifier = modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
        val colors = RadioButtonDefaults.colors(AppTheme.AppColors.buttonChecked)
        RadioButton(onClick = onClick, selected = selected, colors = colors)

        val textStyle = TextStyle(fontSize = Dimens.sp_text, color = AppTheme.AppColors.textMain, fontFamily = Fonts.medium)
        Text(text = text, style = textStyle)
    }
}

@Composable
private fun DividerHorizontal(label: String) {
    val borderColor = AppTheme.AppColors.borderChecked
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Divider(modifier = Modifier.width(Dimens.space_xxx).height(borderWidth), color = borderColor)

            val textStyle = TextStyle(fontSize = Dimens.sp_text, color = AppTheme.AppColors.textMain, fontFamily = Fonts.bold)
            Text(modifier = Modifier.padding(horizontal = Dimens.space_sm), style = textStyle, text = label)

            Divider(modifier = Modifier.fillMaxWidth().height(borderWidth), color = borderColor)
        }

        val modifier = Modifier.weight(1.0f)
        Spacer(modifier = modifier)

        Divider(modifier = Modifier.fillMaxWidth().height(borderWidth), color = borderColor)
    }
}

@Composable
private fun DividerVertical() {
    val borderColor = AppTheme.AppColors.borderChecked
    val modifier = Modifier.padding(top = Dimens.space_norm)
    Row(modifier = modifier) {
        Divider(modifier = Modifier.width(borderWidth).fillMaxHeight(), color = borderColor)

        val modifier = Modifier.weight(1.0f)
        Spacer(modifier = modifier)

        Divider(modifier = Modifier.width(borderWidth).fillMaxHeight(), color = borderColor)
    }
}