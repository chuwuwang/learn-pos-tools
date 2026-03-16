package com.sea.pos.ui.iso8583

import com.sea.pos.ui.BaseViewModel

class TagDecodeViewModel : BaseViewModel<TagDecodeState, Any>() {

    override fun initialState(): TagDecodeState {
        return TagDecodeState()
    }

}