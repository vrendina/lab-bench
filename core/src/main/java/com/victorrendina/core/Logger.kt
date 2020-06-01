package com.victorrendina.core

import javax.inject.Inject

class Logger @Inject constructor(
    private val writers: Set<@JvmSuppressWildcards LogWriter>
) {

    // Not using a lazy val because of the stacktrace
    private var _tag: String? = null
    private val tag: String
        get() {
            if (_tag == null) {
                val element = Throwable().stackTrace.first {
                    it.className != Logger::class.java.name
                }

                _tag = element.className.substringAfterLast(".").substringBefore("$")
            }

            return _tag!!
        }

    fun v(tag: String, throwable: Throwable?, message: String?) {
        log(Priority.VERBOSE, tag, throwable, message)
    }

    fun v(tag: String, message: String) = v(tag, null, message)

    fun v(message: String) = v(tag, message)

    fun d(message: String) = d(tag, message)

    fun d(tag: String, throwable: Throwable?, message: String?) {
        log(Priority.DEBUG, tag, throwable, message)
    }

    fun d(tag: String, message: String) = d(tag, null, message)

    fun w(tag: String, throwable: Throwable?, message: String?) {
        log(Priority.WARN, tag, throwable, message)
    }

    fun w(tag: String, message: String) = w(tag, null, message)

    fun e(throwable: Throwable? = null, message: String? = null) = e(tag, throwable, message)

    fun e(tag: String, throwable: Throwable?, message: String?) {
        log(Priority.ERROR, tag, throwable, message)
    }

    fun e(tag: String, message: String) = e(tag, null, message)

    fun log(priority: Priority, tag: String, throwable: Throwable?, message: String?) {
        if (throwable == null && message == null) return
        writers.forEach { writer ->
            if (writer.isLoggable(priority, tag)) {
                writer.write(priority, tag, throwable, message ?: throwable?.message)
            }
        }
    }

    enum class Priority {
        VERBOSE, DEBUG, WARN, ERROR;

        fun isGreaterThan(priority: Priority): Boolean {
            return this > priority
        }

        fun isAtLeast(priority: Priority): Boolean {
            return this >= priority
        }
    }
}
