package com.sea.pos.ui.algorithm

import com.sea.pos.algorithm.DataFormat

sealed class CommonAlgoIntent {

    class SwitchAlgo(val algo: String) : CommonAlgoIntent()

    class SwitchFormat(val format: DataFormat) : CommonAlgoIntent()

    class InputComponent1(val component: String) : CommonAlgoIntent()

    class InputComponent2(val component: String) : CommonAlgoIntent()

    class InputData(val data: String) : CommonAlgoIntent()

    object Calculate : CommonAlgoIntent()

}