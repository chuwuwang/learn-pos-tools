package com.sea.pos.ui.algorithm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sea.pos.ui.widget.*
import com.sea.pos.utils.I18nUtils

@Composable
fun CommonAlgoActivity() {
    val algoList = listOf(
        I18nUtils.string("common_algo_xor"),
        I18nUtils.string("common_algo_xor_bitwise"),
        I18nUtils.string("common_algo_base64_encode"),
        I18nUtils.string("common_algo_base64_decode"),
    )

    Column {
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

    RwSubtitleText("Component 1")

    val height = Modifier.height(120.dp)

    RwInputTextWithLength(height, component1Text.value, Int.MAX_VALUE) {
        component1Text.value = it
    }

    RwSubtitleText("Component 2")

    RwInputTextWithLength(height, component2Text.value, Int.MAX_VALUE) {
        component2Text.value = it
    }

    RwSubtitleText("Output")

    RwInputTextWithLength(height, outputText.value, Int.MAX_VALUE) {
        outputText.value = it
    }

    RwTextCheckedButton(UiUtils.modifierSpace_xxx, "Done") {}
}