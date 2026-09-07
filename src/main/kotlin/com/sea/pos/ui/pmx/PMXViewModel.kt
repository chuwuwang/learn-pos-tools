package com.sea.pos.ui.pmx

import com.sea.pos.ui.BaseViewModel

internal class PMXViewModel : BaseViewModel<PMXState, Any>() {

    override fun initialState(): PMXState {
        val state = PMXState(
            feature = PMXFeature.ACTIVE,
            activateReq = ActivateReq(),
        )
        return state
    }

    fun dispatch(intent: PMXIntent) {
        when (intent) {
            is PMXIntent.SwitchFeature -> switchFeature(intent)
            is PMXIntent.InputRequestUrl -> inputRequestUrl(intent)
            is PMXIntent.InputActiveParameter -> inputActiveParameter(intent)
            PMXIntent.Active -> active()
            is PMXIntent.OutputPublicKey -> outputPublicKey(intent)
            is PMXIntent.OutputPrivateKey -> outputPrivateKey(intent)
        }
    }

    private fun active() {

    }

    private fun outputPublicKey(intent: PMXIntent.OutputPublicKey) {
        setState { copy(publicKey = intent.text) }
    }

    private fun outputPrivateKey(intent: PMXIntent.OutputPrivateKey) {
        setState { copy(privateKey = intent.text) }
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

}