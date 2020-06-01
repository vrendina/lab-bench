package com.victorrendina.labbench.framework.logging

import android.os.Build
import android.util.Log
import com.victorrendina.core.LogWriter
import com.victorrendina.core.Logger

/**
 * Log writer that writes incoming log messages to the Android logcat. If a threshold priority
 * is specified then any messages with a priority greater than or equal to the specified priority will
 * be logged.
 */
class LogcatWriter(private val threshold: Logger.Priority? = null) : LogWriter {
    override fun isLoggable(priority: Logger.Priority, tag: String): Boolean {
        return if (threshold == null) true else priority.isAtLeast(threshold)
    }

    override fun write(priority: Logger.Priority, tag: String, throwable: Throwable?, message: String?) {
        val shortTag = if (tag.length <= MAX_TAG_LENGTH || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tag
        } else tag.substring(0, MAX_TAG_LENGTH)
        when (priority) {
            Logger.Priority.VERBOSE -> Log.v(shortTag, message, throwable)
            Logger.Priority.DEBUG -> Log.d(shortTag, message, throwable)
            Logger.Priority.WARN -> Log.w(shortTag, message, throwable)
            Logger.Priority.ERROR -> Log.e(shortTag, message, throwable)
        }
    }

    companion object {
        private const val MAX_TAG_LENGTH = 23
    }
}
