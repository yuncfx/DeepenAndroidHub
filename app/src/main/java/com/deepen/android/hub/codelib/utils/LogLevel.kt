package com.deepen.android.hub.codelib.utils

enum class LogLevel(val str: String, private val mIntVal: Int) {
    LEVEL_START("N", 0), LEVEL_VERBOSE("V", 1), LEVEL_INFO("I", 2), LEVEL_DEBUG("D", 3), LEVEL_WARNING("W", 4), LEVEL_ERROR("E", 5), LEVEL_ASSERT("A", 6), LEVEL_END("N", 7);

    fun notLower(level: LogLevel): Boolean {
        return mIntVal >= level.mIntVal
    }

}