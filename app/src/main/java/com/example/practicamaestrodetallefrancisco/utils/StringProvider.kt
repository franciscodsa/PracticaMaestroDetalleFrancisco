package com.example.practicamaestrodetallefrancisco.utils

import android.content.Context
import androidx.annotation.StringRes

class StringProvider(val context: Context) {
    companion object {
        fun instances(context: Context): StringProvider =
            StringProvider(context)
    }

    fun getString(@StringRes stringResId: Int):String{
        return context.getString(stringResId)
    }
}