package com.sea.pos.ui.more

import androidx.compose.ui.graphics.toComposeImageBitmap
import com.sea.pos.algorithm.DataFormat
import com.sea.pos.ui.BaseViewModel
import org.jetbrains.skia.Image
import qrcode.QRCode
import qrcode.color.Colors

class ImageTransformViewModel : BaseViewModel<ImageTransformState, Any>() {

    override fun initialState(): ImageTransformState {
        return ImageTransformState(feature = "QRCode Generate", format = DataFormat.Raw)
    }

    fun dispatch(intent: ImageTransformIntent) {
        when (intent) {
            ImageTransformIntent.GenerateQRCode -> generateQRCode()
            is ImageTransformIntent.InputData -> inputData(intent)
            is ImageTransformIntent.SwitchFeature -> switchFeature(intent)
        }
    }

    private fun generateQRCode() {
        val bgColor = Colors.css("#07C160")
        val bytes = QRCode.ofSquares()
            .withSize(10)
            .withColor(bgColor)
            .build(state.value.inputData)
            .render()
            .getBytes()
        val imageBitmap = Image.makeFromEncoded(bytes).toComposeImageBitmap()
        setState { copy(bitmap = imageBitmap) }
    }

    private fun inputData(intent: ImageTransformIntent.InputData) {
        setState { copy(inputData = intent.data) }
    }

    private fun switchFeature(intent: ImageTransformIntent.SwitchFeature) {
        setState { copy(feature = intent.feature, inputData = "", bitmap = null) }
    }

}