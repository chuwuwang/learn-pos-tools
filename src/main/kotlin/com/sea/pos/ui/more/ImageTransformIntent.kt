package com.sea.pos.ui.more

import com.sea.pos.algorithm.DataFormat

sealed class ImageTransformIntent {

    object Reset : ImageTransformIntent()

    object ImagePicker : ImageTransformIntent()

    object Base64ToImage : ImageTransformIntent()

    object ImageToBase64 : ImageTransformIntent()

    object GenerateQRCode : ImageTransformIntent()

    class InputData(val data: String) : ImageTransformIntent()

    class SwitchFeature(val feature: String) : ImageTransformIntent()

    class SwitchFormat(val format: DataFormat) : ImageTransformIntent()

}