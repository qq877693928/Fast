package com.lizhenhua.fast.plugin.util

import org.objectweb.asm.Type
import org.objectweb.asm.commons.AdviceAdapter

object OpcodeUtil {
    fun getLoadOpcode(type: Type): Int {
        when (type.sort) {
            Type.BOOLEAN -> {
                return AdviceAdapter.ILOAD
            }
            Type.INT -> {
                return AdviceAdapter.ILOAD
            }
            Type.BYTE -> {
                return AdviceAdapter.ILOAD
            }
            Type.SHORT -> {
                return AdviceAdapter.ILOAD
            }
            Type.LONG -> {
                return AdviceAdapter.LLOAD
            }
            Type.FLOAT -> {
                return AdviceAdapter.FLOAD
            }
            Type.DOUBLE -> {
                return AdviceAdapter.DLOAD
            }
            Type.CHAR -> {
                return AdviceAdapter.ILOAD
            }
            else -> {
                return AdviceAdapter.ALOAD
            }
        }
    }
}
