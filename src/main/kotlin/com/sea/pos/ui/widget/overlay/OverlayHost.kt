package com.sea.pos.ui.widget.overlay

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*

@ExperimentalMaterial3Api
@Composable
fun OverlayHost(content: @Composable () -> Unit) {
    var dialog by remember { mutableStateOf<AppDialog>(AppDialog.None) }
    var toast by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        DialogManager.dialogFlow.collect { dialog = it }
    }

    LaunchedEffect(Unit) {
        ToastManager.toastFlow.collect {
            toast = it
        }
    }

    Box {
        content()

        when (dialog) {
            is AppDialog.Error -> RwErrorDialog(dialog = dialog )
            else -> {}
        }

    }
}