package com.sea.pos.utils

import java.text.MessageFormat
import java.util.*

object I18nUtils {

    private fun bundle(): ResourceBundle {
        val locale = Locale.getDefault()
        return ResourceBundle.getBundle("strings", locale)
    }

    fun string(key: String): String {
        return bundle().getString(key)
    }

    fun string(key: String, vararg args: Any): String {
        return MessageFormat.format(bundle().getString(key), *args)
    }

}