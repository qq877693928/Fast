package com.lizhenhua.fast.plugin.util

import org.objectweb.asm.Type

object ParamUtil {
    fun appendParamType(type: Type, params: MutableList<String>) {
        when (type.sort) {
            Type.BOOLEAN -> {
                params.add("java.lang.Boolean")
            }
            Type.INT -> {
                params.add("java.lang.Integer")
            }
            Type.BYTE -> {
                params.add("java.lang.Byte")
            }
            Type.SHORT -> {
                params.add("java.lang.Short")
            }
            Type.LONG -> {
                params.add("java.lang.Long")
            }
            Type.FLOAT -> {
                params.add("java.lang.Float")
            }
            Type.DOUBLE -> {
                params.add("java.lang.Double")
            }
            Type.CHAR -> {
                params.add("java.lang.Character")
            }
            else -> {
                params.add(type.className)
            }
        }
    }
}