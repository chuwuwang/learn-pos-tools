package com.sea.pos

import com.sea.pos.ui.ViewModelStore

class AppController {

    val viewModelStore = ViewModelStore()

    fun clear() {
        viewModelStore.clear()
    }

}