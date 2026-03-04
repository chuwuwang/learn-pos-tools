package com.sea.pos.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<S  : Any, E : Any> {

    private val job = SupervisorJob()

    val viewModelScope = CoroutineScope(Dispatchers.Main.immediate + job)

    // UI State
    abstract fun initialState(): S

    private val _state = MutableStateFlow(initialState())
    val state: StateFlow<S> = _state.asStateFlow()

    // One-shot events (toast, navigation etc.)
    private val _event = MutableSharedFlow<E>()
    val event: SharedFlow<E> = _event.asSharedFlow()

    fun setState(reducer: S.() -> S) {
        _state.value = _state.value.reducer()
    }

    suspend fun sendEvent(event: E) {
        _event.emit(event)
    }

    open fun onCleared() {

    }

    internal fun clear() {
        job.cancel()
        onCleared()
    }

}