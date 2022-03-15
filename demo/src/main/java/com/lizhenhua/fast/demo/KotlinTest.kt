package com.lizhenhua.fast.demo

import com.lizhenhua.fast.annotation.FastLog
import com.lizhenhua.fast.runtime.FastTraceLog
import java.util.*

open class KotlinTest {

    @FastLog
    constructor(value: Long, value1: Long, value2: Long) {
        val var4 = System.currentTimeMillis()
    }

    @FastLog
    constructor(value: String, value1: String, value2: String) {
        val var4 = System.currentTimeMillis()
    }

    @FastLog
    open fun logLong(value: Long?, value2: Long?) {
        val var4 = System.currentTimeMillis()
        println(var4)
    }

    companion object {

        @FastLog
        fun logValue2(value: Long, value2: Long, value3: Long) {
            val var4 = System.currentTimeMillis()
            println(var4)
        }

        fun logValue2(value: String, value2: String, value3: String) {
            FastTraceLog.enterMethod(
                "com.lizhenhua.fast.demo.LogTest",
                "logValue2",
                "[java.lang.String, java.lang.String, java.lang.String]",
                Arrays.asList<Any>(value, value2, value3)
            )
            val var4 = System.currentTimeMillis()
            println(var4)
            FastTraceLog.exitMethod(
                "com.lizhenhua.fast.demo.LogTest",
                "logValue2",
                null as Any?,
                false
            )
        }
    }
}
