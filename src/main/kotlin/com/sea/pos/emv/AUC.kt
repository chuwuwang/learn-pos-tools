package com.sea.pos.emv

enum class AUC(val position: String, val code: String) {

    B1b8("18", "Valid for domestic cash transactions"),
    B1b7("17", "Valid for international cash transactions"),
    B1b6("16", "Valid for domestic goods"),
    B1b5("15", "Valid for international goods"),
    B1b4("14", "Valid for domestic services"),
    B1b3("13", "Valid for international services"),
    B1b2("12", "Valid at ATMs"),
    B1b1("11", "Valid at terminals other than ATMs"),

    B2b8("28", "Domestic cashback allowed"),
    B2b7("27", "International cashback allowed"),
    B2b6("26", "RFU"),
    B2b5("25", "RFU"),
    B2b4("24", "RFU"),
    B2b3("23", "RFU"),
    B2b2("22", "RFU"),
    B2b1("21", "RFU"),

}