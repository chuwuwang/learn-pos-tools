package com.pos.encode.extension

val String.valid: Boolean
    get() = isNotBlank()

val String ?.empty: Boolean
    get() = isNullOrBlank()