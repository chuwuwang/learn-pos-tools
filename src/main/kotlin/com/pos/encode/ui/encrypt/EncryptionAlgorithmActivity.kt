package com.pos.encode.ui.encrypt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pos.encode.Algorithm
import com.pos.encode.com.pos.encode.ui.CommonUiUtil
import com.pos.encode.ui.TopBar
import com.pos.encode.ui.TopBar.topBarItemView
import com.pos.encode.extension.valid
import com.pos.encode.theme.AppTheme
import com.pos.encode.ui.theme.Dimens
import com.pos.encode.ui.theme.Strings
import com.pos.encode.ui.widget.ButtonHelper
import com.pos.encode.ui.widget.TextFieldHelper

object EncryptionAlgorithmActivity {

    private const val DES_DES = 0
    private const val DES_3DES = 1
    private const val AES_128 = 10
    private const val AES_192 = 11
    private const val AES_256 = 12

    private const val PADDING_NoPadding = "NoPadding"
    private const val PADDING_TBCPadding = "TBCPadding"
    private const val PADDING_X923Padding = "X923Padding"
    private const val PADDING_PKCS5Padding = "PKCS5Padding"
    private const val PADDING_PKCS7Padding = "PKCS7Padding"
    private const val PADDING_ZeroBytePadding = "ZeroBytePadding"
    private const val PADDING_ISO10126Padding = "ISO10126Padding"
    private const val PADDING_ISO7816d4Padding = "ISO7816d4Padding"
    private const val PADDING_ISO10126d2Padding = "ISO10126d2Padding"

    @Composable
    fun preview(modifier: Modifier) {
        val algorithmText = remember { mutableStateOf(0) }
        val modeText = remember { mutableStateOf(Algorithm.MODE_ECB) }
        val paddingText = remember { mutableStateOf(PADDING_NoPadding) }
        val formatterText = remember { mutableStateOf(Algorithm.HEXADECIMAL) }

        val inputText = remember { mutableStateOf("") }
        val outputText = remember { mutableStateOf("") }
        val visibleDialog = remember { mutableStateOf(false) }

        Column(modifier) {
            var params = Modifier.fillMaxWidth().height(Dimens.topBarHeight).background(AppTheme.colors.bgContent)

            showTopBar(algorithmText)

            CommonUiUtil.horizontalDivider()

            CommonUiUtil.showEncryptionModeView(modeText)

            CommonUiUtil.showInputDataFormatView(formatterText)

            ButtonHelper.radioGroup(
                Modifier.fillMaxWidth().padding(Dimens.marginStart, Dimens.marginTop, TextFieldHelper.HINT_TEXT_WIDTH, 0.dp),
                Strings.padding,
                ButtonHelper.RADIO_GROUP_HEIGHT + 64.dp
            ) {
                Column {
                    params = Modifier.height(ButtonHelper.RADIO_GROUP_HEIGHT).fillMaxWidth()
                        .padding(0.dp, ButtonHelper.TEXT_MARGIN_BORDER, 0.dp, 0.dp)
                    Row(params) {
                        ButtonHelper.radioButton(Strings.padding_No, paddingText.value == PADDING_NoPadding) {
                            paddingText.value = PADDING_NoPadding
                        }
                        ButtonHelper.radioButton(Strings.padding_PKCS5, paddingText.value == PADDING_PKCS5Padding) {
                            paddingText.value = PADDING_PKCS5Padding
                        }
                        ButtonHelper.radioButton(Strings.padding_PKCS7, paddingText.value == PADDING_PKCS7Padding) {
                            paddingText.value = PADDING_PKCS7Padding
                        }
                        ButtonHelper.radioButton(
                            Strings.padding_ZeroByte, paddingText.value == PADDING_ZeroBytePadding
                        ) {
                            paddingText.value = PADDING_ZeroBytePadding
                        }
                        ButtonHelper.radioButton(Strings.padding_TBC, paddingText.value == PADDING_TBCPadding) {
                            paddingText.value = PADDING_TBCPadding
                        }
                    }
                    params = Modifier.height(64.dp).fillMaxWidth()
                    Row(params) {
                        ButtonHelper.radioButton(Strings.padding_X923, paddingText.value == PADDING_X923Padding) {
                            paddingText.value = PADDING_X923Padding
                        }
                        ButtonHelper.radioButton(
                            Strings.padding_ISO7816d4, paddingText.value == PADDING_ISO7816d4Padding
                        ) {
                            paddingText.value = PADDING_ISO7816d4Padding
                        }
                        ButtonHelper.radioButton(
                            Strings.padding_ISO10126, paddingText.value == PADDING_ISO10126Padding
                        ) {
                            paddingText.value = PADDING_ISO10126Padding
                        }
                        ButtonHelper.radioButton(
                            Strings.padding_ISO10126d2, paddingText.value == PADDING_ISO10126d2Padding
                        ) {
                            paddingText.value = PADDING_ISO10126d2Padding
                        }
                    }
                }
            }

            ButtonHelper.radioGroup(
                Modifier.fillMaxWidth().padding(Dimens.marginStart, Dimens.marginTop, TextFieldHelper.HINT_TEXT_WIDTH, 0.dp),
                Strings.data_format
            ) {
                params = Modifier.fillMaxWidth().padding(0.dp, ButtonHelper.TEXT_MARGIN_BORDER, 0.dp, 0.dp)
                Row(params) {
                    ButtonHelper.radioButton(Strings.data_format_ascii, formatterText.value == Algorithm.ASCII) {
                        formatterText.value = Algorithm.ASCII
                    }
                    ButtonHelper.radioButton(
                        Strings.data_format_hexadecimal, formatterText.value == Algorithm.HEXADECIMAL
                    ) {
                        formatterText.value = Algorithm.HEXADECIMAL
                    }
                }
            }

            TextFieldHelper.inputTextField(
                Modifier.weight(3.0f).padding(0.dp, Dimens.paddingTop, 0.dp, 0.dp),
                Strings.data_input,
                inputText.value,
                Int.MAX_VALUE
            ) { inputText.value = it }

            TextFieldHelper.inputTextField(
                Modifier.weight(1.0f).padding(0.dp, Dimens.paddingTop, 0.dp, 0.dp),
                Strings.data_output,
                outputText.value,
                Int.MAX_VALUE
            ) { outputText.value = it }

            params = Modifier.fillMaxWidth().padding(Dimens.marginStart, Dimens.marginTop, Dimens.marginEnd, Dimens.marginBottom)
            Row(params) {
                ButtonHelper.encryptButton {
                    val text = inputText.value
                    if (text.valid) {

                    } else {
                        visibleDialog.value = true
                    }
                }
                ButtonHelper.decryptButton {

                }
            }

        }
    }

    @Composable
    private fun showTopBar(algorithmText: MutableState<Int>) {
        TopBar.showTopBar {
            topBarItemView(Strings.des3des_des, DES_DES, algorithmText.value) { algorithmText.value = DES_DES }
            topBarItemView(Strings.des3des_3des, DES_3DES, algorithmText.value) { algorithmText.value = DES_3DES }
            topBarItemView(Strings.aes_128, AES_128, algorithmText.value) { algorithmText.value = AES_128 }
            topBarItemView(Strings.aes_192, AES_192, algorithmText.value) { algorithmText.value = AES_192 }
            topBarItemView(Strings.aes_256, AES_256, algorithmText.value) { algorithmText.value = AES_256 }
        }
    }

}