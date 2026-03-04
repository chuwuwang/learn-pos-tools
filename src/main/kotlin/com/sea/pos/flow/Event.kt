package com.sea.pos.flow

sealed class Event {

    class ShowToast(val entity: ToastEntity) : Event()

}