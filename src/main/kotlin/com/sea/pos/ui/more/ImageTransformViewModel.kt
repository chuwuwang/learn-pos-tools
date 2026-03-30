package com.sea.pos.ui.more

import androidx.compose.ui.graphics.toComposeImageBitmap
import com.pos.encode.utils.ByteUtils
import com.sea.pos.algorithm.DataFormat
import com.sea.pos.extension.isInvalidInput
import com.sea.pos.ui.BaseViewModel
import com.sea.pos.ui.widget.overlay.AppDialog
import com.sea.pos.ui.widget.overlay.DialogManager
import com.sea.pos.utils.Base64Utils
import org.jetbrains.skia.Image
import qrcode.QRCode
import qrcode.color.Colors

class ImageTransformViewModel : BaseViewModel<ImageTransformState, Any>() {

    override fun initialState(): ImageTransformState {
        return ImageTransformState(feature = "QRCode Generate", format = DataFormat.Raw)
    }

    fun dispatch(intent: ImageTransformIntent) {
        when (intent) {
            ImageTransformIntent.Base64ToImage -> base64ToImage()
            ImageTransformIntent.GenerateQRCode -> generateQRCode()
            is ImageTransformIntent.InputData -> inputData(intent)
            is ImageTransformIntent.SwitchFormat -> switchFormat(intent)
            is ImageTransformIntent.SwitchFeature -> switchFeature(intent)
        }
    }

    private fun base64ToImage() {
        val inputData = state.value.inputData
        val invalid = inputData.isInvalidInput(state.value.format)
        if (invalid) {
            val dialog = AppDialog.Error(message = "Data error")
            DialogManager.show(dialog)
            return
        }
        launch {
            val imageBitmap = io {
                val bytes = if (state.value.format == DataFormat.Raw) {
                    val string = inputData.substringAfter("base64,")
                    Base64Utils.base64ToBytes(string) ?: byteArrayOf()
                } else {
                    ByteUtils.hexString2Bytes(inputData)
                }
                Image.makeFromEncoded(bytes).toComposeImageBitmap()
            }
            setState { copy(bitmap = imageBitmap) }
        }
    }

    private fun generateQRCode() {
        launch {
            val imageBitmap = io {
                val bgColor = Colors.css("#07C160")
                val bytes = QRCode.ofSquares()
                    .withSize(10)
                    .withColor(bgColor)
                    .build(state.value.inputData)
                    .render()
                    .getBytes()
                Image.makeFromEncoded(bytes).toComposeImageBitmap()
            }
            setState { copy(bitmap = imageBitmap) }
        }
    }

    private fun inputData(intent: ImageTransformIntent.InputData) {
        setState { copy(inputData = intent.data) }
    }

    private fun switchFormat(intent: ImageTransformIntent.SwitchFormat) {
        setState { copy(format = intent.format) }
    }

    private fun switchFeature(intent: ImageTransformIntent.SwitchFeature) {
        setState { copy(feature = intent.feature, inputData = "", bitmap = null) }
    }

}