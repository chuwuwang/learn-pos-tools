package com.sea.pos.emv

enum class TagDecode(val tag: String, val description: String, val length: Int, val format: String) {

    TVR("95", "TVR (Terminal Verification Results)", 10, "10 Hex Digits"),

    AIP("82", "AIP (Application Interchange Profile)", 4, "4 Hex Digits"),

    TerminalCapabilities("9F33", "Terminal Capabilities", 6, "6 Hex Digits"),

    CVM("9F34", "CVM (Cardholder Verification Method)", 6, "6 Hex Digits"),

    CTQ("9F6C", "CTQ (Card Transaction Qualifiers)", 4, "4 Hex Digits"),

    TTQ("9F66", "TTQ (Terminal Transaction Qualifiers)", 8, "8 Hex Digits"),

    TSI("9B", "TSI (Transaction Status Information)", 4, "4 Hex Digits"),

    ATC("9F40", "ATC (Additional Terminal Capabilities)", 10, "10 Hex Digits"),

    AUC("9F07", "AUC (Application Usage Control)", 4, "4 Hex Digits"),

}