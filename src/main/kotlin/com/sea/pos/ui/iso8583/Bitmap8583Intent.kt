package com.sea.pos.ui.iso8583

sealed class Bitmap8583Intent {

    class ClickItem(val index: Int) : Bitmap8583Intent()

    class InputData(val bitmap: String) : Bitmap8583Intent()

    object Generate : Bitmap8583Intent()

    object Reset : Bitmap8583Intent()

}