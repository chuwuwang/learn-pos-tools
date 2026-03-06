package com.sea.pos.ui.iso8583

import com.pos.encode.util.ByteUtil
import com.sea.pos.ui.BaseViewModel
import com.sea.pos.ui.widget.overlay.AppDialog
import com.sea.pos.ui.widget.overlay.DialogManager

class ISO8583BitmapViewModel : BaseViewModel<ISO8583BitmapState, Any>() {

    override fun initialState(): ISO8583BitmapState {
        val bitmap = "0000000000000000"
        val bytes = ByteUtil.hexString2Bytes(bitmap)
        val booleans = ByteUtil.bytes2BinaryBytes(bytes)
        return ISO8583BitmapState(bitmap, booleans)
    }

    fun dispatch(intent: ISO8583BitmapIntent) {
        when (intent) {
            is ISO8583BitmapIntent.ClickBitmapItem -> clickItem(intent)
            is ISO8583BitmapIntent.InputBitmap -> inputBitmap(intent)
            ISO8583BitmapIntent.GenerateBitmap -> generateBitmap()
            ISO8583BitmapIntent.ResetBitmap -> reset()
        }
    }

    private fun clickItem(intent: ISO8583BitmapIntent.ClickBitmapItem) {
        val bytes = getDynamicBitmap(state.value.bitmapBooleans, intent.index)
        val hexString = ByteUtil.bytes2HexString(bytes)
        val booleans = ByteUtil.bytes2BinaryBytes(bytes)
        setState { copy(bitmapString = hexString, bitmapBooleans = booleans) }
    }

    private fun inputBitmap(intent: ISO8583BitmapIntent.InputBitmap) {
        setState { copy(bitmapString = intent.bitmap) }
    }

    private fun generateBitmap() {
        val bitmapString = state.value.bitmapString
        val length = bitmapString.length
        if (length != 16 && length != 32) {
            val dialog = AppDialog.Error(message = "The size of the Bitmap can only be 16 or 32")
            DialogManager.show(dialog)
        } else {
            val bytes = ByteUtil.hexString2Bytes(bitmapString)
            val booleans = ByteUtil.bytes2BinaryBytes(bytes)
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
        if (index != 1) return ByteUtil.binaryBytes2Bytes(bitmaps)
        if (item) {
            val temp = ByteArray(8)
            val oldBytes = ByteUtil.binaryBytes2Bytes(bitmaps)
            System.arraycopy(oldBytes, 0, temp, 0, temp.size)
            return temp
        } else {
            val temp = ByteArray(16)
            val oldBytes = ByteUtil.binaryBytes2Bytes(bitmaps)
            System.arraycopy(oldBytes, 0, temp, 0, oldBytes.size)
            return temp
        }
    }

}

sealed class ISO8583BitmapIntent {

    class ClickBitmapItem(val index: Int) : ISO8583BitmapIntent()

    class InputBitmap(val bitmap: String) : ISO8583BitmapIntent()

    object GenerateBitmap : ISO8583BitmapIntent()

    object ResetBitmap : ISO8583BitmapIntent()

}

data class ISO8583BitmapState(
    val bitmapString: String,
    val bitmapBooleans: BooleanArray,
)