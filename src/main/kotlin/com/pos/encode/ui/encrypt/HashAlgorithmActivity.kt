package com.pos.encode.ui.encrypt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.pos.encode.Algorithm
import com.pos.encode.algorithm.MD5Util
import com.pos.encode.algorithm.SHAUtil
import com.pos.encode.com.pos.encode.ui.CommonUiUtil
import com.pos.encode.com.pos.encode.ui.widget.ButtonUtil
import com.pos.encode.com.pos.encode.ui.widget.InputTextFieldUtil
import com.pos.encode.ui.TopBar
import com.pos.encode.ui.TopBar.topBarItemView
import com.pos.encode.extension.valid
import com.pos.encode.ui.theme.Dimens
import com.pos.encode.ui.theme.Strings
import com.pos.encode.ui.widget.DialogHelper
import com.pos.encode.util.ByteUtil

object HashAlgorithmActivity {

    private const val MD2 = 0
    private const val MD4 = 1
    private const val MD5 = 2
    private const val SHA_1 = 10
    private const val SHA_224 = 11
    private const val SHA_256 = 12
    private const val SHA_384 = 13
    private const val SHA_512 = 14

    @Composable
    fun preview(modifier: Modifier) {
        val algorithmText = remember { mutableStateOf(0) }
        val formatterText = remember { mutableStateOf(Algorithm.HEXADECIMAL) }

        val inputText = remember { mutableStateOf("") }
        val outputText = remember { mutableStateOf("") }
        val visibleDialog = remember { mutableStateOf(false) }

        Column(modifier) {
            showTopBar(algorithmText)

            CommonUiUtil.horizontalDivider()

            CommonUiUtil.showInputDataFormatView(formatterText)

            CommonUiUtil.showHintText(Strings.data_input)
            val inputModifier = Modifier.weight(3.0f).padding(start = Dimens.marginStart, top = Dimens.paddingTop)
            InputTextFieldUtil.showInputTextFieldWithLength(inputModifier, inputText.value) { inputText.value = it }

            CommonUiUtil.showHintText(Strings.data_output)
            val outputModifier = Modifier.weight(1.0f).padding(start = Dimens.marginStart, top = Dimens.paddingTop)
            InputTextFieldUtil.showInputTextFieldWithLength(outputModifier, outputText.value) { outputText.value = it }

            val onButtonClickListener = {
                val text = inputText.value
                if (text.valid) {
                    sha(algorithmText.value, formatterText.value, text, outputText)
                } else {
                    visibleDialog.value = true
                }
            }
            val buttonModifier = Modifier.padding(Dimens.marginStart, Dimens.marginTop, Dimens.marginEnd, Dimens.marginBottom)
            ButtonUtil.encryptButton(buttonModifier, onButtonClickListener)

            DialogHelper.errorDialog(Strings.error_data, visibleDialog)
        }
    }

    @Composable
    private fun showTopBar(algorithmText: MutableState<Int>) {
        TopBar.showTopBar {
            topBarItemView(Strings.hash_md2, MD2, algorithmText.value) { algorithmText.value = MD2 }
            topBarItemView(Strings.hash_md4, MD4, algorithmText.value) { algorithmText.value = MD4 }
            topBarItemView(Strings.hash_md5, MD5, algorithmText.value) { algorithmText.value = MD5 }
            topBarItemView(Strings.hash_sha_1, SHA_1, algorithmText.value) { algorithmText.value = SHA_1 }
            topBarItemView(Strings.hash_sha_224, SHA_224, algorithmText.value) { algorithmText.value = SHA_224 }
            topBarItemView(Strings.hash_sha_256, SHA_256, algorithmText.value) { algorithmText.value = SHA_256 }
            topBarItemView(Strings.hash_sha_384, SHA_384, algorithmText.value) { algorithmText.value = SHA_384 }
            topBarItemView(Strings.hash_sha_512, SHA_512, algorithmText.value) { algorithmText.value = SHA_512 }
        }
    }

    private fun sha(type: Int, dataFormatter: String, data: String, outputText: MutableState<String>) {
        val dataBytes = if (dataFormatter == Algorithm.HEXADECIMAL) {
            ByteUtil.hexString2Bytes(data)
        } else {
            data.toByteArray(Charsets.US_ASCII)
        }
        val result = if (type == MD4) {
            MD5Util.md4(dataBytes)
        } else if (type == MD5) {
            MD5Util.md5(dataBytes)
        } else if (type == SHA_1) {
            SHAUtil.sha1(dataBytes)
        } else if (type == SHA_224) {
            SHAUtil.sha224(dataBytes)
        } else if (type == SHA_256) {
            SHAUtil.sha256(dataBytes)
        } else if (type == SHA_384) {
            SHAUtil.sha384(dataBytes)
        } else if (type == SHA_512) {
            SHAUtil.sha512(dataBytes)
        } else {
            MD5Util.md2(dataBytes)
        }
        if (result != null) {
            val hexString = ByteUtil.bytes2HexString(result)
            outputText.value = hexString
        } else {
            outputText.value = Strings.error_encryption_failed
        }
    }

}