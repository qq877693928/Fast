package com.lizhenhua.fast.plugin.core

import com.lizhenhua.fast.plugin.strategy.MethodStrategy
import com.lizhenhua.fast.plugin.strategy.MethodStrategyFactory
import com.lizhenhua.fast.plugin.util.InvokeUtil
import com.lizhenhua.fast.plugin.util.OpcodeUtil
import com.lizhenhua.fast.plugin.util.StringUtil
import org.objectweb.asm.*
import org.objectweb.asm.commons.AdviceAdapter
import java.util.*

internal class FastMethodVisitor(
    api: Int,
    methodVisitor: MethodVisitor,
    access: Int,
    methodName: String?,
    descriptor: String?,
    className: String
) : AdviceAdapter(api, methodVisitor, access, methodName, descriptor) {
  private val mMethodStrategy: MethodStrategy = MethodStrategyFactory.getMethodStrategy(
    StringUtil.getString(className),
    access,
    StringUtil.getString(methodName),
    StringUtil.getString(descriptor)
  )

  override fun visitAnnotation(descriptor: String, visible: Boolean): AnnotationVisitor {
    mMethodStrategy.visitAnnotation(descriptor)
    return super.visitAnnotation(descriptor, visible)
  }

  public override fun onMethodEnter() {
    super.onMethodEnter()
    mMethodStrategy.visitMethodEnter(mv)
  }

  override fun onMethodExit(opcode: Int) {
    mMethodStrategy.visitMethodExit(this.nextLocal, mv, opcode)
    super.onMethodExit(opcode)
  }
}