package com.pos.encode.ui.encrypt

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.pos.encode.ui.common.HorizontalDivider
import com.pos.encode.ui.common.Topbar
import com.pos.encode.ui.common.TopbarItemView
import com.pos.encode.ui.theme.Strings

private const val XOR = 0
private const val XOR_BITWISE = 1
private const val BASE64_ENCODE = 2
private const val BASE64_DECODE = 10

@Composable
fun CommonAlgorithmActivity(modifier: Modifier) {
    val algoText = remember { mutableStateOf(XOR) }

    Column(modifier) {

        Topbar {
            TopbarItemView(Strings.common_algo_xor, XOR, algoText.value) {
                algoText.value = XOR
            }
            TopbarItemView(Strings.common_algo_xor_bitwise, XOR_BITWISE, algoText.value) {
                algoText.value = XOR_BITWISE
            }
            TopbarItemView(Strings.common_algo_base64_encode, BASE64_ENCODE, algoText.value) {
                algoText.value = BASE64_ENCODE
            }
            TopbarItemView(Strings.common_algo_base64_decode, BASE64_DECODE, algoText.value) {
                algoText.value = BASE64_DECODE
            }
        }

        HorizontalDivider()

    }
}