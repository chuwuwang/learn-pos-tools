package com.sea.pos.ui.algorithm

import com.sea.pos.algorithm.DataFormat
import com.sea.pos.algorithm.SymmetricEncryption
import com.sea.pos.algorithm.SymmetricMode
import com.sea.pos.algorithm.SymmetricPadding

sealed class DESAlgoIntent {

    class SwitchMode(val mode: SymmetricMode) : DESAlgoIntent()

    class SwitchFormat(val format: DataFormat) : DESAlgoIntent()

    class SwitchAlgo(val algo: SymmetricEncryption) : DESAlgoIntent()

    class SwitchPadding(val padding: SymmetricPadding) : DESAlgoIntent()

    class InputIV(val iv: String) : DESAlgoIntent()

    class InputKey(val key: String) : DESAlgoIntent()

    class InputData(val data: String) : DESAlgoIntent()

    object Encrypt : DESAlgoIntent()

    object Decrypt : DESAlgoIntent()

}