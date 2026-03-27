package com.sea.pos.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import com.sea.pos.AppController

class ViewModelStore {

    private val map = mutableMapOf<String, BaseViewModel<*, *> >()

    fun <T : BaseViewModel<*, *> > get(key: String, factory: () -> T): T {
        return map.getOrPut(key) { factory() } as T
    }

    fun <T : BaseViewModel<*, *> > get(key: String): T ? {
        return map[key] as T ?
    }

    fun clear() {
        map.values.forEach { it.clear() }
        map.clear()
    }

}

@Composable
inline fun <reified T : BaseViewModel<*, *> > viewModel(key: String, store: ViewModelStore, noinline factory: () -> T): T {
    val viewModel = remember { store.get(key, factory) }
    DisposableEffect(key1 = Unit) {
        onDispose { viewModel.onCleared() }
    }
    return viewModel
}

@Composable
inline fun <reified T : BaseViewModel<*, *> > viewModel(container: AppController, key: String = T::class.simpleName ?: "ViewModel", noinline factory: () -> T): T {
    val viewModel = remember { container.viewModelStore.get(key, factory) }
    DisposableEffect(key1 = Unit) {
        onDispose { viewModel.onCleared() }
    }
    return viewModel
}