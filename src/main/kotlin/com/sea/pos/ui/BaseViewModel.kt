package com.sea.pos.ui

import com.sea.pos.ui.widget.overlay.AppDialog
import com.sea.pos.ui.widget.overlay.DialogManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<S : Any, E : Any> : CoroutineScope {

    // UI State
    abstract fun initialState(): S

    private val _state = MutableStateFlow(
        value = initialState()
    )
    val state: StateFlow<S> = _state.asStateFlow()

    fun setState(reducer: S.() -> S) {
        _state.value = _state.value.reducer()
    }

    // One-shot events (toast, navigation etc.)
    private val _event = MutableSharedFlow<E>()
    val event: SharedFlow<E> = _event.asSharedFlow()

    suspend fun sendEvent(event: E) {
        _event.emit(event)
    }

    internal fun clear() {
        job.cancel()
        onCleared()
    }

    open fun onCleared() {

    }

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job + CoroutineExceptionHandler { _, e ->
        onError(e)
    }

    open fun onError(e: Throwable) {
        val dialog = AppDialog.Error(message = "Unknown error - " + e.message)
        DialogManager.show(dialog)
    }

    fun launch(block: suspend CoroutineScope.() -> Unit): Job = launch(context = coroutineContext) {
        try {
            block()
        } catch (e: Throwable) {
            e.printStackTrace()
            onError(e)
        }
    }

    fun launchIO(block: suspend CoroutineScope.() -> Unit): Job = launch {
        withContext(Dispatchers.IO) { block() }
    }

    suspend fun <T> io(block: suspend () -> T): T {
        return withContext(Dispatchers.IO) { block() }
    }

    suspend fun <T> main(block: suspend () -> T): T {
        return withContext(Dispatchers.Main) { block() }
    }

}