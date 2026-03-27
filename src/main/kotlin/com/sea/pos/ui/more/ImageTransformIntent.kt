package com.sea.pos.ui.more

sealed class ImageTransformIntent {

    class SwitchFeature(val feature: String) : ImageTransformIntent()

    class InputData(val data: String) : ImageTransformIntent()

    object GenerateQRCode : ImageTransformIntent()

}