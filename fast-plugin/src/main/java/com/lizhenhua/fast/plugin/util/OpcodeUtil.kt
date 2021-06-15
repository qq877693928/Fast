package com.lizhenhua.fast.plugin.util

import jdk.internal.org.objectweb.asm.Opcodes
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

    fun getLoadOpcode(opcode: Int): Int {
        return if (opcode >= Opcodes.IRETURN && opcode <= Opcodes.ARETURN) {
            when (opcode) {
                Opcodes.IRETURN -> Opcodes.ILOAD
                Opcodes.LRETURN -> Opcodes.LLOAD
                Opcodes.FRETURN -> Opcodes.FLOAD
                Opcodes.DRETURN -> Opcodes.DLOAD
                Opcodes.ARETURN -> Opcodes.ALOAD
                else -> Opcodes.NOP
            }
        } else {
            Opcodes.NOP
        }
    }

    fun getStoreOpcode(opcode: Int): Int {
        return if (opcode >= Opcodes.IRETURN && opcode <= Opcodes.ARETURN) {
            when (opcode) {
                Opcodes.IRETURN -> Opcodes.ISTORE
                Opcodes.LRETURN -> Opcodes.LSTORE
                Opcodes.FRETURN -> Opcodes.FSTORE
                Opcodes.DRETURN -> Opcodes.DSTORE
                Opcodes.ARETURN -> Opcodes.ASTORE
                else -> Opcodes.NOP
            }
        } else {
            Opcodes.NOP
        }
    }

    fun hasReturnValue(opcode: Int): Boolean {
        return opcode in AdviceAdapter.IRETURN..AdviceAdapter.DRETURN
    }

    fun voidReturnValue(returnType: Type): Boolean {
        return Type.VOID_TYPE === returnType
    }
}