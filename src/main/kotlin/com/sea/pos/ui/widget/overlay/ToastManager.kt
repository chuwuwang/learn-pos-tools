package com.sea.pos.ui.widget.overlay

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

object ToastManager {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _toastFlow = MutableSharedFlow<String>()
    val toastFlow = _toastFlow.asSharedFlow()

    fun show(message: String) {
        scope.launch { _toastFlow.emit(message) }
    }

}