package com.sea.pos.flow

data class ToastEntity(
    val title: String = "",
    val message: String = "",
    val type: ToastType = ToastType.INFO,
)

enum class ToastType {

    INFO, SUCCESS, ERROR

}