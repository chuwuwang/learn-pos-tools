package com.sea.pos.emv

enum class ATC(val position: String, val code: String) {

    B1b8("18", "Cash"),
    B1b7("17", "Goods"),
    B1b6("16", "Services"),
    B1b5("15", "Cashback"),
    B1b4("14", "Inquiry"),
    B1b3("13", "Transfer"),
    B1b2("12", "Payment"),
    B1b1("11", "Administrative"),

    B2b8("28", "Cash Deposit"),
    B2b7("27", "RFU"),
    B2b6("26", "RFU"),
    B2b5("25", "RFU"),
    B2b4("24", "RFU"),
    B2b3("23", "RFU"),
    B2b2("22", "RFU"),
    B2b1("21", "RFU"),

    B3b8("38", "Numeric keys"),
    B3b7("37", "Alphabetical and special characters keys"),
    B3b6("36", "Command keys"),
    B3b5("35", "Function keys"),
    B3b4("34", "RFU"),
    B3b3("33", "RFU"),
    B3b2("32", "RFU"),
    B3b1("31", "RFU"),

    B4b8("48", "Print, attendant"),
    B4b7("47", "Print, cardholder"),
    B4b6("46", "Display, attendant"),
    B4b5("45", "Display, cardholder"),
    B4b4("44", "RFU"),
    B4b3("43", "RFU"),
    B4b2("42", "Code table 10"),
    B4b1("41", "Code table 9"),

    B5b8("58", "Code table 8"),
    B5b7("57", "Code table 7"),
    B5b6("56", "Code table 6"),
    B5b5("55", "Code table 5"),
    B5b4("54", "Code table 4"),
    B5b3("53", "Code table 3"), 
    B5b2("52", "Code table 2"),
    B5b1("51", "Code table 1"),

}