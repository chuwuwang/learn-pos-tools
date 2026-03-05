package com.sea.pos.ui.widget.overlay

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

object DialogManager {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _dialogFlow = MutableSharedFlow<AppDialog>(extraBufferCapacity = 10)
    val dialogFlow: SharedFlow<AppDialog> = _dialogFlow.asSharedFlow()

    fun show(dialog: AppDialog) {
        scope.launch { _dialogFlow.emit(dialog) }
    }

    fun dismiss() {
        scope.launch { _dialogFlow.emit(AppDialog.None) }
    }

}