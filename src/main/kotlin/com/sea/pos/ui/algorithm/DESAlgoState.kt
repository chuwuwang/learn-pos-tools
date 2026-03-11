package com.sea.pos.ui.algorithm

import com.sea.pos.algorithm.DataFormat
import com.sea.pos.algorithm.SymmetricEncryption
import com.sea.pos.algorithm.SymmetricMode
import com.sea.pos.algorithm.SymmetricPadding

data class DESAlgoState(
    val format: DataFormat,
    val mode: SymmetricMode,
    val algo: SymmetricEncryption,
    val padding: SymmetricPadding,
    val iv: String = "",
    val key: String = "",
    val inputData: String = "",
    val outputData: String = "",
)