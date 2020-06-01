package com.victorrendina.core

interface LogWriter {
    /**
     * Determine if an incoming log message should be written by this log writer.
     */
    fun isLoggable(priority: Logger.Priority, tag: String): Boolean
    /**
     * The write method is invoked if [isLoggable] returns true. This method should be used to write the log
     * message to the appropriate output.
     */
    fun write(priority: Logger.Priority, tag: String, throwable: Throwable?, message: String?)
}
