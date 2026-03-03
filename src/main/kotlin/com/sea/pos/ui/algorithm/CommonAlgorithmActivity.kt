package com.sea.pos.ui.algorithm

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sea.pos.ui.widget.HorizontalDivider
import com.sea.pos.ui.widget.Topbar
import com.sea.pos.utils.I18nUtils

@Composable
fun CommonAlgorithmActivity(modifier: Modifier) {
    val algoList = listOf(
        I18nUtils.string("common_algo_xor"),
        I18nUtils.string("common_algo_xor_bitwise"),
        I18nUtils.string("common_algo_base64_encode"),
        I18nUtils.string("common_algo_base64_decode"),
    )
    Column(modifier) {
        Topbar(algoList) {

        }

        HorizontalDivider()

    }
}