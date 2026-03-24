package com.sea.pos.emv

object EMVTagCollections {

    val map: MutableMap< String, Pair<String, String> > = mutableMapOf()

    init {
        map["4F"] = Pair("Application Identifier / AID", "Card")

        map["50"] = Pair("Application Label / 应用标识", "Card")
        map["56"] = Pair("Track 1 Data / 磁道 1", "Card")
        map["57"] = Pair("Track 2 Data / 磁道 2", "Card")
        map["58"] = Pair("Track 3 Data / 磁道 3", "Card")
        map["59"] = Pair("Card Expiration Date / 卡片有效期", "Card")
        map["5A"] = Pair("Application Primary Account Number (PAN) / 卡号", "Card")
        map["5F20"] = Pair("Cardholder Name / 持卡人姓名", "Card")
        map["5F24"] = Pair("Application Expiration Date / 卡片有效期", "Card")
        map["5F28"] = Pair("Issuer Country Code / 发卡行国家代码", "Card")
        map["5F2A"] = Pair("Transaction Currency Code / 交易货币代码", "Terminal")
        map["5F30"] = Pair("Service Code / 卡片服务码", "Card")
        map["5F34"] = Pair("Card Sequence Number / 卡片序列号", "Card")
        map["5F36"] = Pair("Transaction Currency Exponent / 交易货币指数", "Terminal")

        map["71"] = Pair("Issuer Script Template 1 / 发卡行脚本数据 1", "Issuer")
        map["72"] = Pair("Issuer Script Template 2 / 发卡行脚本数据 2", "Issuer")

        map["82"] = Pair("Application Interchange Profile (AIP) / 应用交互特征", "Card")
        map["84"] = Pair("Dedicated File (DF) Name / 专用DF文件名称", "Card")
        map["89"] = Pair("Authorization Code / 主机授权号", "Issuer")
        map["8A"] = Pair("Authorisation Response Code (ARC) / 主机响应码", "Issuer")

        map["91"] = Pair("Issuer Authentication Data / 发卡行认证数据", "Issuer")
        map["95"] = Pair("Terminal Verification Results (TVR) / 终端验证结果", "Terminal")
        map["9A"] = Pair("Transaction Date / 交易日期", "Terminal")
        map["9B"] = Pair("Transaction Status Information (TSI) / 交易状态信息", "Terminal")
        map["9C"] = Pair("Transaction Type / 交易类型", "Terminal")

        map["9F02"] = Pair("Authorised Amount / 交易金额", "Terminal")
        map["9F03"] = Pair("Other Amount / 附加金额", "Terminal")
        map["9F06"] = Pair("Application Identifier (AID) / AID", "Terminal")
        map["9F08"] = Pair("Application Version Number / 应用版本号", "Card")
        map["9F09"] = Pair("Application Version Number / 应用版本号", "Terminal")
        map["9F0D"] = Pair("Issuer Action Code - Default", "Card")
        map["9F0E"] = Pair("Issuer Action Code - Denial", "Card")
        map["9F0F"] = Pair("Issuer Action Code - Online", "Card")

        map["9F10"] = Pair("Issuer Application Data (IAD) / 发卡行应用数据", "Card")
        map["9F12"] = Pair("Application Preferred Name / 应用名称", "Card")
        map["9F15"] = Pair("Merchant Category Code (MCC)", "Terminal")
        map["9F1A"] = Pair("Terminal Country Code / 终端国家代码", "Terminal")
        map["9F1B"] = Pair("Terminal Floor Limit / 交易最低限额", "Terminal")
        map["9F1D"] = Pair("Terminal Risk Management Data / 终端风险管理数据", "Terminal")
        map["9F1E"] = Pair("Interface Device (IFD) Serial Number / 终端序列号", "Terminal")

        map["9F21"] = Pair("Transaction Time / 交易时间", "Terminal")
        map["9F22"] = Pair("Certification Authority Public Key Index (PKI) / 认证机构公钥索引", "Terminal")
        map["9F26"] = Pair("Application Cryptogram (AC) / 应用密文数据", "Terminal")
        map["9F27"] = Pair("Cryptogram Information Data (CID) / 密文信息数据", "Terminal")

        map["9F33"] = Pair("Terminal Capabilities / 终端性能", "Terminal")
        map["9F34"] = Pair("Cardholder Verification Method (CVM) Results / 持卡人验证结果", "Terminal")
        map["9F35"] = Pair("Terminal Type / 终端类型", "Terminal")
        map["9F36"] = Pair("Application Transaction Counter (ATC) / 应用交易计数器", "Terminal")
        map["9F37"] = Pair("Unpredictable Number (UN) / 不可预知数", "Terminal")
        map["9F40"] = Pair("Additional Terminal Capabilities (ATC) / 附加终端性能", "Terminal")
        map["9F41"] = Pair("Transaction Sequence Counter / 交易序列计数器", "Terminal")
        map["9F50"] = Pair("Cardholder Verification Status / 持卡人验证结果", "Card")
        map["9F5F"] = Pair("DS Slot Availability / DS 插槽可用性", "Card")
        map["9F66"] = Pair("Terminal Transaction Qualifiers (TTQ) / 终端交易限定符", "Terminal")
        map["9F6C"] = Pair("Card Transaction Qualifiers (CTQ) / 卡片交易限定符", "Card")
        map["9F6E"] = Pair("Form Factor Indicator (qV" + "SDC)", "Card/Terminal")
        map["9F7B"] = Pair("VLP Terminal Transaction Limit / 终端交易限额", "Terminal")

        map["DF02"] = Pair("PEK Version Number / PEK 版本号", "Card")
        map["DF03"] = Pair("PIN Try Limit / 密码尝试限制", "Card")
        map["DF04"] = Pair("PIN Try Counter (V" + "SDC Application) / PIN尝试计数器", "Card")
        map["DF05"] = Pair("AIP - For VISA Contactless / AIP-用于VISA非接触式", "Card")
        map["DF06"] = Pair("Products permitted / 允许使用的产品", "Card")
        map["DF07"] = Pair("Offline checks mandated / 规定进行离线检查", "Card")
        map["DF51"] = Pair("Grand Parent AC", "Card")

        map["FF30"] = Pair("Application Label / 应用标识", "Card")
        map["FF31"] = Pair("Application Preferred Name / 应用首选名称", "Card")
    }

}