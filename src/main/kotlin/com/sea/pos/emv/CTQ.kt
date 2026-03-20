package com.sea.pos.emv

enum class CTQ(val position: String, val code: String) {

    B1b8("18", "Online PIN required"),
    B1b7("17", "Signature required"),
    B1b6("16", "Go online if Offline Data Authentication fails and reader is online capable"),
    B1b5("15", "Switch interface if Offline Data Authentication fails and reader supports contact chip"),
    B1b4("14", "Go online if application expired"),
    B1b3("13", "Switch interface for (manual) cash transactions"),
    B1b2("12", "Switch interface for cashback transactions"),
    B1b1("11", "Valid for contactless ATM transactions"),

    B2b8("28", "(CDCVM) Consumer device CVM performed"),
    B2b7("27", "Card supports issuer update processing at the POS"),
    B2b6("26", "RFU"),
    B2b5("25", "RFU"),
    B2b4("24", "RFU"),
    B2b3("23", "RFU"),
    B2b2("22", "RFU"),
    B2b1("21", "RFU"),

}