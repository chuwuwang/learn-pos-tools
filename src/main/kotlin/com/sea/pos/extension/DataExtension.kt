package com.sea.pos.extension

import com.sea.pos.algorithm.DataFormat

fun String.isInputInvalid(format: DataFormat): Boolean {
    val bool = format == DataFormat.Hex && this.valid && this.length % 2 != 0
    return this.empty || bool
}