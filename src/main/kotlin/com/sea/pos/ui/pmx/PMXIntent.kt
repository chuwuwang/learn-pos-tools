package com.sea.pos.ui.pmx

internal sealed class PMXIntent {

    class SwitchFeature(val feat: PMXFeature) : PMXIntent()

    class InputRequestUrl(val text: String) : PMXIntent()

    class InputActiveParameter(val req: ActivateReq) : PMXIntent()

    object Active : PMXIntent()

}