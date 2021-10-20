package com.lizhenhua.fast.plugin.core

import com.lizhenhua.fast.plugin.processor.MethodProcessor
import com.lizhenhua.fast.plugin.processor.MethodProcessorFactory
import com.lizhenhua.fast.plugin.util.StringUtil
import org.objectweb.asm.*
import org.objectweb.asm.commons.AdviceAdapter

internal class FastMethodVisitor(
    api: Int,
    methodVisitor: MethodVisitor,
    access: Int,
    methodName: String?,
    descriptor: String?,
    className: String
) : AdviceAdapter(api, methodVisitor, access, methodName, descriptor) {
  private val mMethodProcessor: MethodProcessor = MethodProcessorFactory.getMethodStrategy(
    StringUtil.getString(className),
    access,
    StringUtil.getString(methodName),
    StringUtil.getString(descriptor)
  )

  override fun visitAnnotation(descriptor: String, visible: Boolean): AnnotationVisitor {
    mMethodProcessor.visitAnnotation(descriptor)
    return super.visitAnnotation(descriptor, visible)
  }

  public override fun onMethodEnter() {
    super.onMethodEnter()
    mMethodProcessor.visitMethodEnter(mv)
  }

  override fun onMethodExit(opcode: Int) {
    mMethodProcessor.visitMethodExit(this.nextLocal, mv, opcode)
    super.onMethodExit(opcode)
  }
}