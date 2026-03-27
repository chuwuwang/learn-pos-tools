package com.sea.pos.ui.more

import androidx.compose.ui.graphics.ImageBitmap
import com.sea.pos.algorithm.DataFormat

data class ImageTransformState(
    val feature: String,
    val format: DataFormat,
    val inputData: String = "",
    val bitmap: ImageBitmap ? = null,
)