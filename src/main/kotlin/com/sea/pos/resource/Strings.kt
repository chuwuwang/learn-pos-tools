package com.sea.pos.resource

object Strings {

    val common_algo: String
        get() = "Common Algorithm"
    val common_algo_xor: String
        get() = "XOR"
    val common_algo_xor_bitwise: String
        get() = "Bitwise XOR"
    val common_algo_base64_encode: String
        get() = "Base64 Encode"
    val common_algo_base64_decode: String
        get() = "Base64 Decode"


    val data_input: String
        get() = "Input Data"
    val data_output: String
        get() = "Output Data"
    val data_format: String
        get() = "Data Input"
    val data_format_ascii: String
        get() = "ASCII"
    val data_format_hexadecimal: String
        get() = "Hexadecimal"

    val mode: String
        get() = "Mode"
    val mode_ECB: String
        get() = "ECB"
    val mode_CBC: String
        get() = "CBC"
    val mode_CFB: String
        get() = "CFB"
    val mode_OFB: String
        get() = "OFB"

    val padding: String
        get() = "Padding"
    val padding_No: String
        get() = "None"
    val padding_PKCS5: String
        get() = "PKCS#5"
    val padding_PKCS7: String
        get() = "PKCS#7"
    val padding_ZeroByte: String
        get() = "Zero"
    val padding_TBC: String
        get() = "TBC"
    val padding_X923: String
        get() = "X923"
    val padding_ISO7816d4: String
        get() = "ISO 7816-4"
    val padding_ISO10126: String
        get() = "ISO 10126"
    val padding_ISO10126d2: String
        get() = "ISO 10126-2"

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    val encryption_algorithm: String
        get() = "Encryption Algorithm"
    val aes: String
        get() = "AES"
    val aes_128: String
        get() = "AES-128"
    val aes_192: String
        get() = "AES-192"
    val aes_256: String
        get() = "AES-256"

    val des3des: String
        get() = "DES/3DES"
    val des3des_des: String
        get() = "DES"
    val des3des_3des: String
        get() = "3DES"

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    val hash_algorithm: String
        get() = "Hash Algorithm"

    val hash_md2: String
        get() = "MD2"
    val hash_md4: String
        get() = "MD4"
    val hash_md5: String
        get() = "MD5"

    val hash_sha: String
        get() = "SHA"
    val hash_sha_1: String
        get() = "SHA-1"
    val hash_sha_224: String
        get() = "SHA-224"
    val hash_sha_256: String
        get() = "SHA-256"
    val hash_sha_384: String
        get() = "SHA-384"
    val hash_sha_512: String
        get() = "SHA-512"

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    val iso8583_bitmap: String
        get() = "ISO8583 Bitmap"


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    val ok: String
        get() = "OK"
    val cancel: String
        get() = "Cancel"
    val yes: String
        get() = "YES"
    val no: String
        get() = "NO"

    val error: String
        get() = "Error"
    val error_data: String
        get() = "Data error"
    val error_encryption_failed: String
        get() = "Encryption failed"

}