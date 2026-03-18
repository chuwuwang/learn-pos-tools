package com.sea.pos.emv

enum class CTQ(val position: String, val code: String) {

    B1Bit8("18", "Online PIN Required"),
    B1Bit7("17", "Signature Required"),
    B1Bit6("16", "Go Online if Offline Data Authentication Fails and Reader is online capable"),
    B1Bit5("15", "Switch Interface if Offline Data Authentication fails and Reader supports contact chip"),
    B1Bit4("14", "Go Online if Application Expired"),
    B1Bit3("13", "Switch Interface for (manual) Cash Transactions"),
    B1Bit2("12", "Switch Interface for Cashback Transactions"),
    B1Bit1("11", "Not valid for contactless ATM transactions"),

    B2Bit8("28", "CDCVM Performed"),
    B2Bit7("27", "Card supports Issuer Update Processing at the POS"),
    B2Bit6("26", "RFU"),
    B2Bit5("25", "RFU"),
    B2Bit4("24", "RFU"),
    B2Bit3("23", "RFU"),
    B2Bit2("22", "RFU"),
    B2Bit1("21", "RFU"),

}