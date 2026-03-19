package com.sea.pos.emv

enum class CVM(val position: String, val code: String) {

    Performed1("18", "RFU"),

    Performed2("171", "Apply succeeding CV Rule if this CVM is unsuccessful"),
    Performed3("170", "Fail cardholder verification if this CVM is unsuccessful"),

    Performed4("000000", "Fail CVM processing"),
    Performed5("000001", "Plaintext PIN verification performed by ICC"),
    Performed6("000010", "Enciphered PIN verified online"),
    Performed7("000011", "Plaintext PIN verification performed by ICC and signature (paper)"),
    Performed8("000100", "Enciphered PIN verification performed by ICC"),
    Performed9("000101", "Enciphered PIN verification performed by ICC and signature (paper)"),
    Performed10("011110", "Signature (paper)"),
    Performed11("011111", "No CVM required"),
    Performed12("111111", "No CVM Performed"),

    Performed13("000110", "Facial biometric verified offline (by ICC)"),
    Performed14("000111", "Facial biometric verified online"),
    Performed15("001000", "Finger biometric verified offline (by ICC)"),
    Performed16("001001", "Finger biometric verified online"),
    Performed17("001010", "Palm biometric verified offline (by ICC)"),
    Performed18("001011", "Palm biometric verified online"),
    Performed19("001100", "Iris biometric verified offline (by ICC)"),
    Performed20("001101", "Iris biometric verified online"),
    Performed21("001110", "Voice biometric verified offline (by ICC)"),
    Performed22("001111", "Voice biometric verified online"),
    PerformedRFU("1XX", "RFU or Reserved for use by individual payment systems"),

    Condition1("200", "Always"),
    Condition2("201", "If unattended cash"),
    Condition3("202", "If not unattended cash and not manual cash and not purchase with cashback"),
    Condition4("203", "If terminal supports the CVM"),
    Condition5("204", "If manual cash"),
    Condition6("205", "If purchase with cashback"),
    Condition7("206", "If transaction is in the application currency and is under X value (see section 10.5 for a discussion of “X”)"),
    Condition8("207", "If transaction is in the application currency and is over X value"),
    Condition9("208", "If transaction is in the application currency and is under Y value (see section 10.5 for a discussion of “Y”)"),
    Condition10("209", "If transaction is in the application currency and is over Y value"),
    ConditionRFU("2XX", "RFU or Reserved for use by individual payment systems"),

    Result1("300", "Unknown"),
    Result2("301", "Failed"),
    Result3("302", "Successful"),
    ResultRFU("3XX", "Unrecognized"),

}