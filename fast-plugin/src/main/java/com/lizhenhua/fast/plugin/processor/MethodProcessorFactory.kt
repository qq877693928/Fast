package com.lizhenhua.fast.plugin.processor

import com.lizhenhua.fast.plugin.info.MethodModel.Companion.parse
import org.objectweb.asm.Opcodes

object MethodProcessorFactory {
    fun getMethodStrategy(
        className: String,
        access: Int,
        methodName: String,
        descriptor: String
    ): MethodProcessor {
        val model = parse(className, access, methodName, descriptor)
        return when {
            isStaticMethod(access) -> {
                StaticMethodProcessor(model)
            }
            "<init>" == methodName -> {
                ConstructorMethodProcessor(model)
            }
            else -> {
                CommonMethodProcessor(model)
            }
        }
    }

    private fun isStaticMethod(access: Int): Boolean {
        return (access and Opcodes.ACC_STATIC) == Opcodes.ACC_STATIC
    }
}