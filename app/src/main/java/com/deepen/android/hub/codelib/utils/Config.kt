package com.deepen.android.hub.codelib.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.util.*

class Config {
    var defaultTag = "ORANGE"
        private set
    var saveLevel = LogLevel.LEVEL_VERBOSE
        private set
    var isReportCrash = true
        private set
    var isSaveLog = true
        private set
    var fileCacheSize = 128 * 1024 * 1024
        private set
    var fileSplitSize = 1024 * 1024
        private set
    var logCacheRootPath = "/sdcard/orange/"
        private set
    var logFileHeader = ""
        private set

    fun setDefaultTag(tag: String): Config {
        defaultTag = tag
        return this
    }

    fun setSaveLevel(level: LogLevel): Config {
        saveLevel = level
        return this
    }

    fun setIsReportCrash(isReportCrash: Boolean): Config {
        this.isReportCrash = isReportCrash
        return this
    }

    fun setIsSaveLog(isSaveLog: Boolean): Config {
        this.isSaveLog = isSaveLog
        return this
    }

    fun setFileCacheSize(size: Int): Config {
        fileCacheSize = size
        return this
    }

    fun setFileSplitSize(size: Int): Config {
        fileSplitSize = size
        return this
    }

    fun setLogCacheRootPath(path: String): Config {
        logCacheRootPath = path
        return this
    }

    fun setLogFileHeader(header: String): Config {
        logFileHeader = header
        return this
    }

    companion object {
        val IS_DEBUG = debuggable
        private val debuggable: Boolean
            private get() {
                val isUserMode = Build.TYPE == "user"
                return !isUserMode
            }

        val LOG_FILE_TIME_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    }
}