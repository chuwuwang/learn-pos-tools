package com.sea.pos.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.sea.pos.AppController

class ViewModelStore {

    private val map = mutableMapOf<String, BaseViewModel<*, *> >()

    fun <T : BaseViewModel<*, *> > get(key: String, factory: () -> T): T {
        return map.getOrPut(key) { factory() } as T
    }

    fun clear() {
        map.values.forEach { it.clear() }
        map.clear()
    }

}

@Composable
inline fun <reified T : BaseViewModel<*, *> > viewModel(key: String, store: ViewModelStore, noinline factory: () -> T): T {
    return remember { store.get(key, factory) }
}

@Composable
inline fun <reified T : BaseViewModel<*, *> > viewModel(container: AppController, key: String = T::class.simpleName ?: "ViewModel", noinline factory: () -> T): T {
    return remember { container.viewModelStore.get(key, factory) }
}