package com.lizhenhua.fast.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.lizhenhua.fast.annotation.FastLog

class MainActivity : AppCompatActivity() {
  @FastLog
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    testHello("hello", "world")
  }

  @FastLog
  private fun testHello(first: String, second: String): String {
    return "$first $second"
  }
}