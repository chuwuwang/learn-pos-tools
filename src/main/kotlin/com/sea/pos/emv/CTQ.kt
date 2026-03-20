package com.sea.pos.emv

enum class CTQ(val position: String, val code: String) {

    B1b8("18", "Online PIN Required"),
    B1b7("17", "Signature Required"),
    B1b6("16", "Go Online if Offline Data Authentication Fails and Reader is online capable"),
    B1b5("15", "Switch Interface if Offline Data Authentication fails and Reader supports contact chip"),
    B1b4("14", "Go Online if Application Expired"),
    B1b3("13", "Switch Interface for (manual) Cash Transactions"),
    B1b2("12", "Switch Interface for Cashback Transactions"),
    B1b1("11", "Valid for contactless ATM transactions"),

    B2b8("28", "CDCVM Performed"),
    B2b7("27", "Card supports Issuer Update Processing at the POS"),
    B2b6("26", "RFU"),
    B2b5("25", "RFU"),
    B2b4("24", "RFU"),
    B2b3("23", "RFU"),
    B2b2("22", "RFU"),
    B2b1("21", "RFU"),

}