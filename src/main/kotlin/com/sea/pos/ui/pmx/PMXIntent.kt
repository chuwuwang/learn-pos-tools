package com.sea.pos.ui.pmx

internal sealed class PMXIntent {

    class SwitchFeature(val feat: PMXFeature) : PMXIntent()

    class InputRequestUrl(val text: String) : PMXIntent()

    class InputActiveParameter(val req: ActivateReq) : PMXIntent()

    object Active : PMXIntent()


    class SwitchCoordinateType(val type: CoordinateType) : PMXIntent()

    class InputLat(val text: String) : PMXIntent()

    class InputLon(val text: String) : PMXIntent()

    object ConvertCoordinate : PMXIntent()

}