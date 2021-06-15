package com.lizhenhua.fast.plugin.util

object StringUtil {
    fun getString(s: String?): String {
        return if (s == null || s.isEmpty()) "" else s
    }
}