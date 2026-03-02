package com.pos.encode.ui.encrypt

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pos.encode.ui.common.HorizontalDivider
import com.pos.encode.ui.common.Topbar
import com.pos.encode.ui.theme.Strings

@Composable
fun CommonAlgorithmActivity(modifier: Modifier) {
    val algoList = listOf(
        Strings.common_algo_xor,
        Strings.common_algo_xor_bitwise,
        Strings.common_algo_base64_encode,
        Strings.common_algo_base64_decode,
    )
    Column(modifier) {
        Topbar(algoList) {

        }

        HorizontalDivider()

    }
}