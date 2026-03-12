package com.sea.pos.ui.algorithm

import com.sea.pos.algorithm.DataFormat

data class CommonAlgoState(
    val algo: String,
    val format: DataFormat,
    val component1: String = "",
    val component2: String = "",
    val inputData: String = "",
    val outputData: String = "",
)