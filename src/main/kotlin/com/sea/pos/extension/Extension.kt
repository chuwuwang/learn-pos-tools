package com.sea.pos.extension

val String.valid: Boolean
    get() = isNotBlank()

val String ?.empty: Boolean
    get() = isNullOrBlank()