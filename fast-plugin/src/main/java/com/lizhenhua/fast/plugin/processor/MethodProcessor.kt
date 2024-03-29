package com.lizhenhua.fast.plugin.processor

import com.lizhenhua.fast.plugin.info.MethodModel
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

abstract class MethodProcessor(methodModel: MethodModel?, open var classIsInject: Boolean) : IMethod, IModel {
    protected var mMethodModel: MethodModel
    override fun visitAnnotation(descriptor: String) {
        if (LOG_CLASS_DESCRIPTOR == descriptor) {
            mMethodModel.isInject = true
        }
    }

    override fun visitMethodEnter(mv: MethodVisitor?) {
        if (!mMethodModel.isInject && !classIsInject) {
            return
        }
        if (mv == null) {
            return
        }
        handleMethodEnter(mv)
    }

    override fun visitMethodExit(nextLocal: Int, mv: MethodVisitor?, opcode: Int) {
        if (mv != null && (mMethodModel.isInject || classIsInject) && Opcodes.ATHROW != opcode) {
            handleMethodExit(nextLocal, mv, opcode)
        }
    }

    override var timeLocalIndex: Int
        get() = mMethodModel.timeLocalIndex
        set(index) {
            mMethodModel.timeLocalIndex = index
        }

    /**
     * 处理方法进入时
     *
     * @param mv 方法访问器
     */
    protected abstract fun handleMethodEnter(mv: MethodVisitor)

    /**
     * 处理方法退出时
     *
     * @param nextLocal 下一个Insn index值
     * @param mv        方法访问器
     * @param opcode    返回操作符
     */
    protected abstract fun handleMethodExit(nextLocal: Int, mv: MethodVisitor, opcode: Int)

    companion object {
        const val LOG_CLASS_DESCRIPTOR = "Lcom/lizhenhua/fast/annotation/FastLog;"
    }

    init {
        requireNotNull(methodModel) { "methodModel is null" }
        mMethodModel = methodModel
    }
}
