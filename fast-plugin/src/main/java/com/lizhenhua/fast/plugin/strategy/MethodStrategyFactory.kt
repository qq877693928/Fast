package com.lizhenhua.fast.plugin.strategy

import com.lizhenhua.fast.plugin.info.MethodModel.Companion.parse
import org.objectweb.asm.Opcodes

object MethodStrategyFactory {
    fun getMethodStrategy(
        className: String,
        access: Int,
        methodName: String,
        descriptor: String
    ): MethodStrategy {
        val model = parse(className, access, methodName, descriptor)
        return when {
            isStaticMethod(access) -> {
                StaticMethodStrategy(model)
            }
            "<init>" == methodName -> {
                ConstructorMethodStrategy(model)
            }
            else -> {
                CommonMethodStrategy(model)
            }
        }
    }

    private fun isStaticMethod(access: Int): Boolean {
        return (access and Opcodes.ACC_STATIC) == Opcodes.ACC_STATIC
    }
}