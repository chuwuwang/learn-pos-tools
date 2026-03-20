package com.sea.pos.emv

enum class TerminalCapabilities(val position: String, val code: String) {

    // Card Data Input Capability
    B1b8("18", "Manual key entry"),
    B1b7("17", "Magnetic stripe"),
    B1b6("16", "IC with contacts"),
    B1b5("15", "RFU"),
    B1b4("14", "RFU"),
    B1b3("13", "RFU"),
    B1b2("12", "RFU"),
    B1b1("11", "RFU"),

    // CVM Capability
    B2b8("28", "Plaintext PIN for ICC verification"),
    B2b7("27", "Enciphered PIN for online verification"),
    B2b6("26", "Signature (paper)"),
    B2b5("25", "Enciphered PIN for offline verification"),
    B2b4("24", "No CVM required"),
    B2b3("23", "RFU"),
    B2b2("22", "RFU"),
    B2b1("21", "RFU"),

    // Security Capability
    B3b8("38", "Static Data Authentication (SDA)"),
    B3b7("37", "Dynamic Data Authentication (DDA)"),
    B3b6("36", "Card capture"),
    B3b5("35", "RFU"),
    B3b4("34", "Combined DDA/Application Cryptogram Generation (CDA)"),
    B3b3("33", "RFU"),
    B3b2("32", "RFU"),
    B3b1("31", "RFU"),

}