package com.sea.pos.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<S : Any, E : Any> {

    val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

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

    open fun onCleared() {

    }

    internal fun clear() {
        viewModelScope.cancel()
        onCleared()
    }

}