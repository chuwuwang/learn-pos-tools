package com.sea.pos.emv

enum class TVR(val position: String, val code: String) {

    B1Bit8("18", "Offline data authentication was not performed"),
    B1Bit7("17", "SDA failed"),
    B1Bit6("16", "ICC data missing"),
    B1Bit5("15", "Card appears on terminal exception file"),
    B1Bit4("14", "DDA failed"),
    B1Bit3("13", "CDA failed"),
    B1Bit2("12", "SDA selected"),
    B1Bit1("11", "RFU"),

    B2Bit8("28", "ICC and terminal have different application versions"),
    B2Bit7("27", "Expired application"),
    B2Bit6("26", "Application not yet effective"),
    B2Bit5("25", "Service not allowed for card product"),
    B2Bit4("24", "New card"),
    B2Bit3("23", "RFU"),
    B2Bit2("22", "RFU"),
    B2Bit1("21", "RFU"),

    B3Bit8("38", "Cardholder verification was not successful"),
    B3Bit7("37", "Unrecognized CVM"),
    B3Bit6("36", "PIN Try Limit exceeded"),
    B3Bit5("35", "PIN entry required and PIN pad not present or not working"),
    B3Bit4("34", "PIN entry required, PIN pad present, but PIN was not entered"),
    B3Bit3("33", "Online PIN entered"),
    B3Bit2("32", "RFU"),
    B3Bit1("31", "RFU"),

    B4Bit8("48", "Transaction exceeds floor limit"),
    B4Bit7("47", "Lower consecutive offline limit exceeded"),
    B4Bit6("46", "Upper consecutive offline limit exceeded"),
    B4Bit5("45", "Transaction selected randomly for online processing"),
    B4Bit4("44", "Merchant forced transaction online"),
    B4Bit3("43", "RFU"),
    B4Bit2("42", "RFU"),
    B4Bit1("41", "RFU"),

    B5Bit8("58", "Default TDOL used"),
    B5Bit7("57", "Issuer authentication failed"),
    B5Bit6("56", "Script processing failed before final GENERATE AC"),
    B5Bit5("55", "Script processing failed after final GENERATE AC"),
    B5Bit4("54", "Relay resistance threshold exceeded"),
    B5Bit3("53", "Relay resistance time limits exceeded"),
    B5Bit2("52", "Relay resistance protocol not supported"),
    B5Bit1("51", "RFU"),

}