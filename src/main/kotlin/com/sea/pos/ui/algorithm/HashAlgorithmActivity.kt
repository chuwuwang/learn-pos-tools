package com.sea.pos.ui.algorithm

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.widget.*

@Composable
fun HashAlgorithmActivity(modifier: Modifier = Modifier) {
    val algoList = listOf("MD2", "MD4", "MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512")
    val outputText = remember { mutableStateOf("") }
    val component1Text = remember { mutableStateOf("") }
    val component2Text = remember { mutableStateOf("") }
    Column(modifier) {
        Topbar(algoList) {

        }
        HorizontalDivider()

        RwRadioGroup(list = listOf("Raw", "Hex"), label = "Data Format") {

        }

        RwSubtitleText("Input Data")

        RwInputField(Modifier.weight(3f), outputText.value, Int.MAX_VALUE) {
            outputText.value = it
        }

        RwSubtitleText("Output Data")
        RwInputField(Modifier.weight(1f), outputText.value, Int.MAX_VALUE) {
            outputText.value = it
        }

        RwDecryptButton(modifier = UiUtils.modifierSpace_xxx) { }

        Vertical(Dimens.space_xxx)
    }
}