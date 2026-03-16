package com.sea.pos.emv

enum class TagDecode(val tag: String, val description: String) {

    TVR("95", "TVR (Terminal Verification Results)"),

    AIP("82", "AIP (Application Interchange Profile)"),

    CVM("9F34", "CVM (Cardholder Verification Method)"),

    CTQ("9F6C", "CTQ (Card Transaction Qualifiers)"),

}