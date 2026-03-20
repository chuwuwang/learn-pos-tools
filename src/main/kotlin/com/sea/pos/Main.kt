package com.sea.pos

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.sea.pos.ui.algorithm.CommonAlgoActivity
import com.sea.pos.ui.algorithm.DESAlgoActivity
import com.sea.pos.ui.algorithm.HashAlgoActivity
import com.sea.pos.ui.iso8583.Bitmap8583Activity
import com.sea.pos.ui.iso8583.TagDecodeActivity
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.theme.SeaTheme
import com.sea.pos.ui.widget.overlay.OverlayHost

@ExperimentalMaterial3Api
fun main() = application {
    val position = WindowPosition.Aligned(Alignment.Center)
    // val windowState = WindowState(size = DpSize.Unspecified, position = position)
    val windowState = WindowState(size = DpSize(1400.dp, 1000.dp), position = position)
    Window(title = "POS Tools", state = windowState, onCloseRequest = ::exitApplication) { App() }
}

@ExperimentalMaterial3Api
@Composable
@Preview
fun App() {
    val controller = remember { AppController() }
    val current = remember { mutableStateOf(0) }
    SeaTheme {
        Row {
            var modifier = Modifier.weight(1f).fillMaxHeight().background(AppTheme.AppColors.bgSidebar)
            Sidebar(modifier, current.value) { current.value = it }

            modifier = Modifier.weight(3f).fillMaxHeight().background(AppTheme.AppColors.bgContent)
            BoxWithConstraints(modifier) { OverlayHost { SwitchScreen(current, controller) } }
        }
    }
}

@Composable
private fun SwitchScreen(index: MutableState<Int>, controller: AppController) {
    if (index.value == Sidebar.MENU_ALGO_HASH) {
        HashAlgoActivity()
    } else if (index.value == Sidebar.MENU_ALGO_DES) {
        DESAlgoActivity()
    } else if (index.value == Sidebar.MENU_ALGO_COMMON) {
        CommonAlgoActivity()
    } else if (index.value == Sidebar.MENU_ISO8583) {
        Bitmap8583Activity(controller = controller)
    } else if (index.value == Sidebar.MENU_TAG_DECODE) {
        TagDecodeActivity(controller = controller)
    }
}