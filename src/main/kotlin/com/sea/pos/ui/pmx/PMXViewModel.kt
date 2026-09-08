package com.sea.pos.ui.pmx

import com.sea.pos.ui.BaseViewModel
import java.math.BigInteger
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.MessageDigest
import java.security.SecureRandom
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.RSAKeyGenParameterSpec
import java.util.*

internal class PMXViewModel : BaseViewModel<PMXState, Any>() {

    override fun initialState(): PMXState {
        val state = PMXState(
            feature = PMXFeature.ACTIVE,
            activateReq = ActivateReq(
                serialNumber = "NISHENZHOU", model = "P3HD", vendor = "SUNMI", encryptedPin = "921354"
            ),
            requestUrl = "https://pay-gate-test-new.payermax.com/aggregate-pay/api/gateway/posActivate",
        )
        return state
    }

    fun dispatch(intent: PMXIntent) {
        when (intent) {
            is PMXIntent.SwitchFeature -> switchFeature(intent)
            is PMXIntent.InputRequestUrl -> inputRequestUrl(intent)
            is PMXIntent.InputActiveParameter -> inputActiveParameter(intent)
            PMXIntent.Active -> active()
        }
    }

    private fun active() {
        launchNetwork {
            val keyPair = generateKeyPair()
            val publicKey = (keyPair.public as RSAPublicKey).encoded
            val privateKey = (keyPair.private as RSAPrivateKey).encoded
            val publicKeyString = Base64.getEncoder().encodeToString(publicKey)
            val privateKeyString = Base64.getEncoder().encodeToString(privateKey)

            val req = state.value.activateReq
            req.clientPublicKey = publicKeyString
            req.encryptedPin = sha256Hex(req.encryptedPin)

            val onSuccess: (ActiveResponse) -> Unit = {
                val data = it.data
                if (data != null) {
                    setState { copy(activateInfo = data, publicKey = publicKeyString, privateKey = privateKeyString) }
                } else {

                }
            }
            val onFailure: (Throwable) -> Unit = {

            }
            val result = PMXFacade.active(state.value.requestUrl, state.value.activateReq)
            result.onSuccess(onSuccess).onFailure(onFailure)
        }
    }

    private fun switchFeature(intent: PMXIntent.SwitchFeature) {
        setState { copy(feature = intent.feat) }
    }

    private fun inputRequestUrl(intent: PMXIntent.InputRequestUrl) {
        setState { copy(requestUrl = intent.text) }
    }

    private fun inputActiveParameter(intent: PMXIntent.InputActiveParameter) {
        setState { copy(activateReq = intent.req) }
    }

    private fun sha256Hex(input: String): String {
        val hash = MessageDigest.getInstance("SHA-256")
            .digest(input.toByteArray(Charsets.UTF_8))
        return hash.joinToString("") { "%02x".format(it) }
    }

    private fun generateKeyPair(): KeyPair {
        val publicExponent = BigInteger("010001", 16)
        val spec = RSAKeyGenParameterSpec(2048, publicExponent)

        val secureRandom = SecureRandom()
        val keyGen = KeyPairGenerator.getInstance("RSA")
        keyGen.initialize(spec, secureRandom)

        return keyGen.generateKeyPair()
    }

}