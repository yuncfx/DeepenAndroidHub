package com.deepen.android.hub.codelib.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * @author shane
 */
object SPUtil {
    const val SP_DIR = "fCard_Sp"
    private val TAG = SPUtil::class.java.simpleName
    fun putLong(context: Context, key: String?, value: Long) {
        val sp = getSharedPreferences(context)
        sp.edit().putLong(key, value).apply()
    }

    fun getLong(context: Context, key: String?, def: Long): Long {
        val sp = getSharedPreferences(context)
        return sp.getLong(key, def)
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(SP_DIR, Context.MODE_PRIVATE)
    }
}