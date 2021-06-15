package com.lizhenhua.fast.plugin.util

import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import org.objectweb.asm.MethodVisitor

object InvokeUtil {
    fun invokeStaticObject(mv: MethodVisitor, type: Type) {
        when (type.sort) {
            Type.BOOLEAN -> mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "java/lang/Boolean",
                "valueOf",
                "(Z)Ljava/lang/Boolean;",
                false
            )
            Type.INT -> mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "java/lang/Integer",
                "valueOf",
                "(I)Ljava/lang/Integer;",
                false
            )
            Type.BYTE -> mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "java/lang/Byte",
                "valueOf",
                "(B)Ljava/lang/Byte;",
                false
            )
            Type.SHORT -> mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "java/lang/Short",
                "valueOf",
                "(S)Ljava/lang/Short;",
                false
            )
            Type.LONG -> mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "java/lang/Long",
                "valueOf",
                "(J)Ljava/lang/Long;",
                false
            )
            Type.FLOAT -> mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "java/lang/Float",
                "valueOf",
                "(F)Ljava/lang/Float;",
                false
            )
            Type.DOUBLE -> mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "java/lang/Double",
                "valueOf",
                "(D)Ljava/lang/Double;",
                false
            )
            Type.CHAR -> mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "java/lang/Character",
                "valueOf",
                "(C)Ljava/lang/Character;",
                false
            )
            else -> {
            }
        }
    }
}