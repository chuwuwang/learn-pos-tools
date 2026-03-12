package com.sea.pos.ui.widget.overlay

sealed class AppDialog(
    val title: String = "",
    val message: String = "",
    val confirmText: String = "Confirm",
    val onConfirm: () -> Unit = { },
    val dismissText: String = "Cancel",
    val onDismiss: () -> Unit = { },
) {

    object None : AppDialog()

    class Error(
        title: String = "",
        message: String,
        confirmText: String = "Confirm",
        onConfirm: () -> Unit = { },
    ) : AppDialog(title, message, confirmText, onConfirm)

}