package com.pos.encode

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.pos.encode.com.pos.encode.ui.home.Sidebar
import com.pos.encode.com.pos.encode.ui.home.showSidebar
import com.pos.encode.com.pos.encode.ui.iso8583.Bitmap8583Screen
import com.pos.encode.ui.encrypt.EncryptionAlgorithmActivity
import com.pos.encode.ui.encrypt.HashAlgorithmActivity
import com.pos.encode.ui.theme.POSTheme
import com.pos.encode.ui.theme.seaTheme

@Composable
@Preview
fun app() {
    val current = remember { mutableStateOf(0) }
    seaTheme {
        Row {
            val onSidebarClick: (Int) -> Unit = { current.value = it }
            val modifierSidebar = Modifier.weight(1f).fillMaxHeight().background(POSTheme.colors.sidebarBackground)
            showSidebar(modifierSidebar, current.value, onSidebarClick)

            val modifierContent = Modifier.weight(3f).fillMaxHeight().background(POSTheme.colors.contentBackground)
            BoxWithConstraints(modifierContent) { switchScreen(current, modifierContent) }
        }
    }
}

@Composable
private fun switchScreen(index: MutableState<Int>, modifier: Modifier) {
    if (index.value == Sidebar.MENU_HASH_ALGORITHM) {
        HashAlgorithmActivity.preview(modifier)
    } else if (index.value == Sidebar.MENU_ENCRYPTION_ALGORITHM) {
        EncryptionAlgorithmActivity.preview(modifier)
    } else if (index.value == Sidebar.MENU_ISO8583_BITMAP) {
        Bitmap8583Screen(modifier)
    }
}

fun main() = application {
    val position = WindowPosition.Aligned(Alignment.Center)
    // val windowState = WindowState(size = DpSize.Unspecified, position = position)
    val windowState = WindowState(size = DpSize(1400.dp, 1000.dp), position = position)
    Window(title = "POS Tools", state = windowState, onCloseRequest = ::exitApplication) { app() }
}