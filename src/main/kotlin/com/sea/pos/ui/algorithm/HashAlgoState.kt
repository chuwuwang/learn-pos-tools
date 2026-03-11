package com.sea.pos.ui.algorithm

import com.sea.pos.algorithm.DataFormat
import com.sea.pos.algorithm.Hash

data class HashAlgoState(
    val algo: Hash,
    val format: DataFormat,
    val inputData: String = "",
    val outputData: String = "",
)