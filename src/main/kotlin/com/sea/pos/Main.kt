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
import com.pos.encode.ui.encrypt.EncryptionAlgorithmActivity
import com.pos.encode.ui.encrypt.HashAlgorithmActivity
import com.sea.pos.ui.algorithm.CommonAlgorithmActivity
import com.sea.pos.ui.algorithm.HashAlgorithmActivity
import com.sea.pos.ui.iso8583.ISO8583BitmapActivity
import com.sea.pos.ui.theme.AppTheme
import com.sea.pos.ui.theme.SeaTheme
import com.sea.pos.ui.widget.Sidebar
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
    val appController = remember { AppController() }
    val current = remember { mutableStateOf(0) }
    SeaTheme {
        Row {
            val modifierSidebar = Modifier.weight(1f).fillMaxHeight().background(AppTheme.AppColors.bgSidebar)
            Sidebar(modifierSidebar, current.value) { current.value = it }

            val modifierContent = Modifier.weight(3f).fillMaxHeight().background(AppTheme.AppColors.bgContent)
            BoxWithConstraints(modifierContent) { OverlayHost { SwitchScreen(appController, current, modifierContent) } }
        }
    }
}

@Composable
private fun SwitchScreen(controller: AppController, index: MutableState<Int>, modifier: Modifier) {
    if (index.value == Sidebar.MENU_HASH_ALGORITHM) {
        HashAlgorithmActivity.preview(modifier)
    } else if (index.value == Sidebar.MENU_ENCRYPTION_ALGORITHM) {
        EncryptionAlgorithmActivity.preview(modifier)
    } else if (index.value == Sidebar.MENU_ISO8583_BITMAP) {
        ISO8583BitmapActivity(modifier, controller)
    } else if (index.value == Sidebar.MENU_COMMON_ALGORITHM) {
        HashAlgorithmActivity(modifier)
    }
}