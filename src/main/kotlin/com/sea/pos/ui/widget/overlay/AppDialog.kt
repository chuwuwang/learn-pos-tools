package com.sea.pos.ui.widget.overlay

sealed class AppDialog(
    val title: String = "",
    val message: String = "",
    val confirmText: String = "CONFIRM",
    val onConfirm: () -> Unit = { },
    val dismissText: String = "CANCEL",
    val onDismiss: () -> Unit = { },
) {

    object None : AppDialog()

    class Error(
        title: String = "",
        message: String,
        confirmText: String = "CONFIRM",
        onConfirm: () -> Unit = { },
    ) : AppDialog(title, message, confirmText, onConfirm)

}