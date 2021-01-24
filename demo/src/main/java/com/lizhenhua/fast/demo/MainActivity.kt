package com.lizhenhua.fast.demo

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.lizhenhua.fast.annotation.FastLog

class MainActivity : Activity() {
    @FastLog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onTestInt(view: View) {
        testInt(1)
    }

    fun onTestBoolean(view: View) {
        testBoolean(true)
    }

    fun onTestIntArray(view: View) {
        testIntArray(arrayOf(1, 2))
    }

    @FastLog
    private fun testIntArray(arrayOf: Array<Int>) {
        println(arrayOf)
    }

    @FastLog
    private fun testInt(int: Int) {
        println(int)
    }

    @FastLog
    private fun testBoolean(b: Boolean) {
        println(b)
    }
}