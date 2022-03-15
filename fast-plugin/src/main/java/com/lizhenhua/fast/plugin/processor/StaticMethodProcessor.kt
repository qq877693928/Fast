package com.lizhenhua.fast.plugin.processor

import com.lizhenhua.fast.plugin.info.MethodModel
import com.lizhenhua.fast.plugin.util.InvokeUtil
import com.lizhenhua.fast.plugin.util.OpcodeUtil
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import org.objectweb.asm.commons.AdviceAdapter

class StaticMethodProcessor(methodModel: MethodModel?, override var classIsInject: Boolean) :
    MethodProcessor(methodModel, classIsInject) {
    override fun handleMethodEnter(mv: MethodVisitor) {
        mv.visitLdcInsn(mMethodModel.className)
        mv.visitLdcInsn(mMethodModel.methodName)
        mv.visitLdcInsn(mMethodModel.paramClassNameList.toString())
        mv.visitInsn(if (mMethodModel.paramSize > 0) (Opcodes.ICONST_0 + mMethodModel.paramSize) else Opcodes.ACONST_NULL)

        var loadIndex = 0

        if (mMethodModel.paramSize > 0) {
            mv.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object")
            for (index in 0 until mMethodModel.paramSize) {
                val type: Type? = mMethodModel.paramTypeList?.get(index)
                if (type != null) {
                    mv.visitInsn(Opcodes.DUP)
                    mv.visitInsn(Opcodes.ICONST_0 + index)
                    mv.visitVarInsn(OpcodeUtil.getLoadOpcode(type), loadIndex)
                    InvokeUtil.invokeStaticObject(mv, type)
                    mv.visitInsn(Opcodes.AASTORE);
                    loadIndex += type.size
                }
            }
            mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "java/util/Arrays",
                "asList",
                "([Ljava/lang/Object;)Ljava/util/List;",
                false
            )
        }
        mv.visitMethodInsn(
            Opcodes.INVOKESTATIC,
            "com/lizhenhua/fast/runtime/FastTraceLog",
            "enterMethod",
            "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V",
            false
        )
    }

    override fun handleMethodExit(nextLocal: Int, mv: MethodVisitor, opcode: Int) {
        mv.visitLdcInsn(mMethodModel.className)
        mv.visitLdcInsn(mMethodModel.methodName)
        mv.visitInsn(Opcodes.ACONST_NULL)
        mv.visitInsn(Opcodes.ICONST_0)

        mv.visitMethodInsn(
            AdviceAdapter.INVOKESTATIC,
            "com/lizhenhua/fast/runtime/FastTraceLog",
            "exitMethod",
            "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Z)V",
            false
        )
    }
}
