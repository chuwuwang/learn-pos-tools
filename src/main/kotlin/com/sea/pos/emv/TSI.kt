package com.sea.pos.emv

enum class TSI(val position: String, val code: String) {

    B1b8("18", "Offline data authentication was performed"),
    B1b7("17", "Cardholder verification was performed"),
    B1b6("16", "Card risk management was performed"),
    B1b5("15", "Issuer authentication was performed"),
    B1b4("14", "Terminal risk management was performed"),
    B1b3("13", "Issuer script processing was performed"),
    B1b2("12", "RFU"),
    B1b1("11", "RFU"),

    B2b8("28", "RFU"),
    B2b7("27", "RFU"),
    B2b6("26", "RFU"),
    B2b5("25", "RFU"),
    B2b4("24", "RFU"),
    B2b3("23", "RFU"),
    B2b2("22", "RFU"),
    B2b1("21", "RFU"),

}