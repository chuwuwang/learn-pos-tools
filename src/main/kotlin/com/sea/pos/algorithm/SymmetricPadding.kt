package com.sea.pos.algorithm

enum class SymmetricPadding(val code: String) {

    PKCS5Padding("PKCS5Padding"),

    PKCS7Padding("PKCS7Padding"),

    NoPadding("NoPadding"),

    ZeroPadding("ZeroPadding"),

    ANSIX923Padding("ANSIX923Padding"),

    ISO10126Padding("ISO10126Padding");

}