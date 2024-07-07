package com.example.lib_common.util

import android.util.Log

class Logger {
    interface Logger {
        fun d(message: String)
        fun d(message: String, e: Exception?)
        fun i(message: String)
        fun i(message: String, e: Exception?)
        fun e(message: String)
        fun e(message: String, e: Exception?)
    }

    class LoginLogger : Logger {
        override fun d(message: String) {
            Log.d(TAG, createMessage(message))
        }

        override fun d(message: String, e: Exception?) {
            Log.d(TAG, createMessage(message))
        }

        override fun i(message: String) {
            Log.i(TAG, createMessage(message))
        }

        override fun i(message: String, e: Exception?) {
            Log.i(TAG, createMessage(message))
        }

        override fun e(message: String) {
            Log.e(TAG, createMessage(message))
        }

        override fun e(message: String, e: Exception?) {
            Log.e(TAG, createMessage(message))
        }

        private fun createMessage(message: String): String {
            if (Throwable().stackTrace.size > 2) {
                val stackTraceElement = Throwable().stackTrace[2]
                return stackTraceElement.className +
                        "." + stackTraceElement.methodName +
                        ":" + stackTraceElement.lineNumber.toString() +
                        " " + message
            }
            return message
        }
    }

    companion object {
        private const val TAG = "loginsdk"
    }

    class DummyLogger : Logger {
        override fun d(message: String) {}
        override fun d(message: String, e: java.lang.Exception?) {}
        override fun i(message: String) {}
        override fun i(message: String, e: java.lang.Exception?) {}
        override fun e(message: String) {}
        override fun e(message: String, e: java.lang.Exception?) {}
    }

    object LoggerProvider {
        private var logger: Logger = LoginLogger()

        fun setLogger(logger: Logger) {
            LoggerProvider.logger = logger
        }
        fun provide(): Logger {
            return logger
        }
    }
}