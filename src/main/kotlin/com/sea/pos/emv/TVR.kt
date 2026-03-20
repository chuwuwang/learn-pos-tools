package com.sea.pos.emv

enum class TVR(val position: String, val code: String) {

    B1b8("18", "Offline data authentication was not performed"),
    B1b7("17", "SDA failed"),
    B1b6("16", "ICC data missing"),
    B1b5("15", "Card appears on terminal exception file"),
    B1b4("14", "DDA failed"),
    B1b3("13", "CDA failed"),
    B1b2("12", "SDA selected"),
    B1b1("11", "RFU"),

    B2b8("28", "ICC and terminal have different application versions"),
    B2b7("27", "Expired application"),
    B2b6("26", "Application not yet effective"),
    B2b5("25", "Requested service not allowed for card product"),
    B2b4("24", "New card"),
    B2b3("23", "RFU"),
    B2b2("22", "RFU"),
    B2b1("21", "RFU"),

    B3b8("38", "Cardholder verification was not successful"),
    B3b7("37", "Unrecognized CVM"),
    B3b6("36", "PIN Try Limit exceeded"),
    B3b5("35", "PIN entry required and PIN pad not present or not working"),
    B3b4("34", "PIN entry required, PIN pad present, but PIN was not entered"),
    B3b3("33", "Online PIN entered"),
    B3b2("32", "RFU"),
    B3b1("31", "RFU"),

    B4b8("48", "Transaction exceeds floor limit"),
    B4b7("47", "Lower consecutive offline limit exceeded"),
    B4b6("46", "Upper consecutive offline limit exceeded"),
    B4b5("45", "Transaction selected randomly for online processing"),
    B4b4("44", "Merchant forced transaction online"),
    B4b3("43", "RFU"),
    B4b2("42", "RFU"),
    B4b1("41", "RFU"),

    B5b8("58", "Default TDOL used"),
    B5b7("57", "Issuer authentication failed"),
    B5b6("56", "Script processing failed before final GENERATE AC"),
    B5b5("55", "Script processing failed after final GENERATE AC"),
    B5b4("54", "Relay resistance threshold exceeded"),
    B5b3("53", "Relay resistance time limits exceeded"),
    B5b2("52", "Relay resistance protocol not supported"),
    B5b1("51", "RFU"),

}