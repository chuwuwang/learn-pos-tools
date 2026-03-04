package com.sea.pos.ui.algorithm

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sea.pos.ui.widget.*
import com.sea.pos.utils.I18nUtils

@Composable
fun CommonAlgorithmActivity(modifier: Modifier = Modifier) {
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

        XORScreen()
    }
}

@Composable
private fun XORScreen() {
    val outputText = remember { mutableStateOf("") }
    val component1Text = remember { mutableStateOf("") }
    val component2Text = remember { mutableStateOf("") }

    RwHintText("Component 1")

    RwInputField(120.dp, component1Text.value, Int.MAX_VALUE) {
        component1Text.value = it
    }

    RwHintText("Component 2")

    RwInputField(120.dp, component2Text.value, Int.MAX_VALUE) {
        component2Text.value = it
    }

    RwHintText("Output")

    RwInputField(120.dp, outputText.value, Int.MAX_VALUE) {
        outputText.value = it
    }

    RwTextButton(UiUtils.modifierSpace_xxx, "Done") {}
}