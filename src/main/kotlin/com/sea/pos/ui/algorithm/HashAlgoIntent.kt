package com.sea.pos.ui.algorithm

import com.sea.pos.algorithm.DataFormat
import com.sea.pos.algorithm.Hash

sealed class HashAlgoIntent {

    class SwitchAlgo(val algo: Hash) : HashAlgoIntent()

    class SwitchFormat(val format: DataFormat) : HashAlgoIntent()

    class InputData(val data: String) : HashAlgoIntent()

    object Encrypt : HashAlgoIntent()

}