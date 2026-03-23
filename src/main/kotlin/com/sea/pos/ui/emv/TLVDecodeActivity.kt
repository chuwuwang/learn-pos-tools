package com.sea.pos.ui.emv

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.sea.pos.AppController
import com.sea.pos.ui.resource.Dimens
import com.sea.pos.ui.viewModel
import com.sea.pos.ui.widget.*

@Composable
fun TLVDecodeActivity(controller: AppController) {
    val vm = viewModel(container = controller) {
        TLVDecodeViewModel()
    }
    val state by vm.state.collectAsState()

    Column {
        RwSubtitleText("Input Data")

        RwInputTextWithLength(modifier = Modifier.weight(2f), value = state.inputData) {
            val intent = TLVDecodeIntent.InputData(it)
            vm.dispatch(intent)
        }

        Row(modifier = UiUtils.modifierSpace_xxx) {
            RwDecryptButton { vm.dispatch(intent = TLVDecodeIntent.Decode) }

            RwHorizontal(width = Dimens.space_x)

            RwDecryptButton { }
        }

        RwVertical(height = Dimens.space_xxx)
    }

}