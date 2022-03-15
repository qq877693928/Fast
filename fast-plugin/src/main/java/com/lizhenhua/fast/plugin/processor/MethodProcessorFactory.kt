package com.lizhenhua.fast.plugin.processor

import com.lizhenhua.fast.plugin.info.MethodModel.Companion.parse
import org.objectweb.asm.Opcodes

object MethodProcessorFactory {
    fun getMethodStrategy(
        className: String,
        access: Int,
        methodName: String,
        descriptor: String,
        classIsInject: Boolean
    ): MethodProcessor {
        val model = parse(className, access, methodName, descriptor)
        return when {
            isStaticMethod(access) -> {
                StaticMethodProcessor(model, classIsInject)
            }
            "<init>" == methodName -> {
                ConstructorMethodProcessor(model, classIsInject)
            }
            else -> {
                CommonMethodProcessor(model, classIsInject)
            }
        }
    }

    private fun isStaticMethod(access: Int): Boolean {
        return (access and Opcodes.ACC_STATIC) == Opcodes.ACC_STATIC
    }
}
