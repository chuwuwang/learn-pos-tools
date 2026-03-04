package com.sea.pos.ui.algorithm

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sea.pos.ui.widget.HorizontalDivider
import com.sea.pos.ui.widget.RwHintText
import com.sea.pos.ui.widget.RwInputField
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
    val component1Text = remember { mutableStateOf("") }
    val component2Text = remember { mutableStateOf("") }
    Column(modifier) {
        Topbar(algoList) {

        }
        HorizontalDivider()
        RwHintText("Component 1")
        RwInputField(120.dp, component1Text.value, Int.MAX_VALUE) {
            component1Text.value = it
        }
        RwHintText("Component 2")
        RwInputField(120.dp, component2Text.value, Int.MAX_VALUE) {
            component2Text.value = it
        }
    }
}