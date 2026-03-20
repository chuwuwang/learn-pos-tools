package com.sea.pos.emv

enum class TTQ(val position: String, val code: String) {

    B1b8("18", "MSD supported"),
    B1b7("17", "RFU"),
    B1b6("16", "qVSDC supported"),
    B1b5("15", "EMV contact chip supported"),
    B1b4("14", "Offline-only reader"),
    B1b3("13", "Online PIN supported"),
    B1b2("12", "Signature supported"),
    B1b1("11", "Offline Data Authentication (ODA) for Online Authorizations supported"),

    B2b8("28", "Online cryptogram required"),
    B2b7("27", "CVM required"),
    B2b6("26", "(Contact Chip) Offline PIN supported"),
    B2b5("25", "RFU"),
    B2b4("24", "RFU"),
    B2b3("23", "RFU"),
    B2b2("22", "RFU"),
    B2b1("21", "RFU"),

    B3b8("38", "Issuer Update Processing supported"),
    B3b7("37", "Mobile functionality supported (CDCVM)"),
    B3b6("36", "RFU"),
    B3b5("35", "RFU"),
    B3b4("34", "RFU"),
    B3b3("33", "RFU"),
    B3b2("32", "RFU"),
    B3b1("31", "RFU"),

    B4b8("48", "RFU"),
    B4b7("47", "RFU"),
    B4b6("46", "RFU"),
    B4b5("45", "RFU"),
    B4b4("44", "RFU"),
    B4b3("43", "RFU"),
    B4b2("42", "RFU"),
    B4b1("41", "RFU"),

}