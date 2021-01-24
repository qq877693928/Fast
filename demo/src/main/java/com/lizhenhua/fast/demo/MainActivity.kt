package com.lizhenhua.fast.demo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.lizhenhua.fast.annotation.FastLog

class MainActivity : Activity() {
    @FastLog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testHello("hello", "world")
        testArray(arrayOf(1, 2))
        testReturnArray()
        testReturnObjectArray()
    }

    @FastLog
    private fun testReturnObjectArray(): Array<Object> {
        return arrayOf(Object(), Object())
    }

    @FastLog
    private fun testReturnArray(): Array<Long> {
        return arrayOf(1L, 2L)
    }

    @FastLog
    private fun testArray(array: Array<Int>) {
        testFun2(array)
    }

    @FastLog
    private fun testFun2(array: Array<Int>) {
        Log.e("" + array[0], "" + array[1])
    }

    @FastLog
    private fun testHello(first: String, second: String): String {
        return "$first $second"
    }
}