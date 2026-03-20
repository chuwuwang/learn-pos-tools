package com.sea.pos.emv

enum class AIP(val position: String, val code: String) {

    B1b8("18", "RFU"),
    B1b7("17", "SDA supported"),
    B1b6("16", "DDA supported"),
    B1b5("15", "Cardholder verification is supported"),
    B1b4("14", "Terminal risk management is to be performed"),
    B1b3("13", "Issuer authentication is supported"),
    B1b2("12", "On device cardholder verification is supported"),
    B1b1("11", "CDA supported"),

    B2b8("28", "EMV Mode has been selected"),
    B2b7("27", "RFU"),
    B2b6("26", "RFU"),
    B2b5("25", "RFU"),
    B2b4("24", "RFU"),
    B2b3("23", "RFU"),
    B2b2("22", "RFU"),
    B2b1("21", "Relay resistance protocol is supported"),

}