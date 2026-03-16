package com.sea.pos.emv

enum class TagDecode(val tag: String, val description: String, val length: Int, val format: String) {

    TVR("95", "TVR (Terminal Verification Results)", 10, "10 Hex Digits"),

    AIP("82", "AIP (Application Interchange Profile)", 4, "4 Hex Digits"),

    CVM("9F34", "CVM (Cardholder Verification Method)", 6, "6 Hex Digits"),

    CTQ("9F6C", "CTQ (Card Transaction Qualifiers)", 4, "4 Hex Digits"),

}