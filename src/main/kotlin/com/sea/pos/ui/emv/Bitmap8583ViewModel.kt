package com.sea.pos.ui.emv

import com.pos.encode.utils.ByteUtils
import com.sea.pos.ui.BaseViewModel
import com.sea.pos.ui.widget.overlay.AppDialog
import com.sea.pos.ui.widget.overlay.DialogManager

class Bitmap8583ViewModel : BaseViewModel<Bitmap8583State, Any>() {

    override fun initialState(): Bitmap8583State {
        val bitmap = "0000000000000000"
        val bytes = ByteUtils.hexString2Bytes(bitmap)
        val booleans = ByteUtils.bitmapBytes2BinaryBytes(bytes)
        return Bitmap8583State(bitmap, booleans)
    }

    fun dispatch(intent: Bitmap8583Intent) {
        when (intent) {
            is Bitmap8583Intent.ClickItem -> clickItem(intent)
            is Bitmap8583Intent.InputData -> inputBitmap(intent)
            Bitmap8583Intent.Generate -> generateBitmap()
            Bitmap8583Intent.Reset -> reset()
        }
    }

    private fun clickItem(intent: Bitmap8583Intent.ClickItem) {
        val bytes = getDynamicBitmap(state.value.bitmapBooleans, intent.index)
        val hexString = ByteUtils.bytes2HexString(bytes)
        val booleans = ByteUtils.bitmapBytes2BinaryBytes(bytes)
        setState { copy(bitmapString = hexString, bitmapBooleans = booleans) }
    }

    private fun inputBitmap(intent: Bitmap8583Intent.InputData) {
        setState { copy(bitmapString = intent.bitmap) }
    }

    private fun generateBitmap() {
        val bitmapString = state.value.bitmapString
        val length = bitmapString.length
        if (length != 16 && length != 32) {
            val dialog = AppDialog.Error(message = "The size of the Bitmap can only be 16 or 32")
            DialogManager.show(dialog)
        } else {
            val bytes = ByteUtils.hexString2Bytes(bitmapString)
            val booleans = ByteUtils.bitmapBytes2BinaryBytes(bytes)
            setState { copy(bitmapBooleans = booleans) }
        }
    }

    private fun reset() {
        val initialState = initialState()
        setState { copy(bitmapString = initialState.bitmapString, bitmapBooleans = initialState.bitmapBooleans) }
    }

    private fun getDynamicBitmap(bitmaps: BooleanArray, index: Int): ByteArray {
        val item = bitmaps[index]
        bitmaps[index] = ! item
        if (index != 1) return ByteUtils.binaryBytes2Bytes(bitmaps)
        if (item) {
            val temp = ByteArray(8)
            val oldBytes = ByteUtils.binaryBytes2Bytes(bitmaps)
            System.arraycopy(oldBytes, 0, temp, 0, temp.size)
            return temp
        } else {
            val temp = ByteArray(16)
            val oldBytes = ByteUtils.binaryBytes2Bytes(bitmaps)
            System.arraycopy(oldBytes, 0, temp, 0, oldBytes.size)
            return temp
        }
    }

}