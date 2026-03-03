package com.pos.encode.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sea.pos.ui.resource.Colors

@Composable
fun radioSelectView(modifier: Modifier, text: String, selected: Boolean, onClick: () -> Unit) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selected, colors = RadioButtonDefaults.colors(Colors.primary), onClick = onClick)
        Text(fontSize = 14.sp, color = Colors.text_main, text = text)
    }
}

@Composable
fun tabView(text: String, tab: Int, currentTab: Int, onClick: () -> Unit) {
    whiteRectangleButton(modifier = Modifier.height(56.dp).fillMaxWidth(), onClick = onClick) {
        Text(fontSize = 16.sp, color = Colors.text_main, text = text)
    }
    divider(tab, currentTab)
}

@Composable
fun textRectangleBorder(text: String, height: Dp = 72.dp, lineColor: Color = Colors.primary_70, lineSize: Dp = 1.5.dp, content: @Composable () -> Unit) {
    Box(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 0.dp).height(height), contentAlignment = Alignment.Center) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Divider(Modifier.width(16.dp).height(lineSize), color = lineColor)
                Text(modifier = Modifier.height(20.dp).padding(4.dp, 0.dp), fontSize = 16.sp, color = Colors.text_main, text = text)
                Divider(Modifier.fillMaxWidth().height(lineSize), color = lineColor)
            }
            val allHeight = Modifier.weight(1.0f)
            Spacer(allHeight)
            Divider(Modifier.fillMaxWidth().height(lineSize), color = lineColor)
        }
        val padding = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
        Row(padding) {
            Divider(Modifier.width(lineSize).fillMaxHeight(), color = lineColor)
            val allHeight = Modifier.weight(1.0f)
            Spacer(allHeight)
            Divider(Modifier.width(lineSize).fillMaxHeight(), color = lineColor)
        }
        content()
    }
}

@Composable
private fun divider(tab: Int, currentTab: Int) {
    if (tab == currentTab) {
        Divider(Modifier.fillMaxWidth().height(3.dp), color = Colors.primary)
    } else {
        Divider(Modifier.fillMaxWidth().height(3.dp), color = Colors.transparent)
    }
}