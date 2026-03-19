package com.sea.pos.emv

enum class AIP(val position: String, val code: String) {

    B1Bit8("18", "RFU"),
    B1Bit7("17", "SDA supported"),
    B1Bit6("16", "DDA supported"),
    B1Bit5("15", "Cardholder verification is supported"),
    B1Bit4("14", "Terminal risk management is to be performed"),
    B1Bit3("13", "Issuer authentication is supported"),
    B1Bit2("12", "On device cardholder verification is supported"),
    B1Bit1("11", "CDA supported"),

    B2Bit8("28", "EMV Mode has been selected"),
    B2Bit7("27", "RFU"),
    B2Bit6("26", "RFU"),
    B2Bit5("25", "RFU"),
    B2Bit4("24", "RFU"),
    B2Bit3("23", "RFU"),
    B2Bit2("22", "RFU"),
    B2Bit1("21", "Relay resistance protocol is supported"),

}