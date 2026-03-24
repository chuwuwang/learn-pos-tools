package com.sea.pos

import com.sea.pos.ui.ViewModelStore

class AppController {

    var navigate: (Int) -> Unit = { }

    val viewModelStore = ViewModelStore()

    fun clear() {
        viewModelStore.clear()
    }

}