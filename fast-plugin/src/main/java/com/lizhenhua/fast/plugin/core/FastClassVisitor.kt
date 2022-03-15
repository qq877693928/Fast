package com.lizhenhua.fast.plugin.core

import com.lizhenhua.fast.plugin.processor.MethodProcessor
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor

internal class FastClassVisitor(accessFlag: Int, classVisitor: ClassVisitor) : ClassVisitor(accessFlag, classVisitor) {
  private var mClassName: String = ""
  private var classIsInject: Boolean = false

  override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
    classIsInject = MethodProcessor.LOG_CLASS_DESCRIPTOR == descriptor
    return super.visitAnnotation(descriptor, visible)
  }

  override fun visit(version: Int, accessFlag: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String>?) {
    super.visit(version, accessFlag, name, signature, superName, interfaces)
    mClassName = name ?: "Unknown"
  }

  override fun visitMethod(access: Int, methodName: String?, descriptor: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor {
    val methodVisitor = cv.visitMethod(access, methodName, descriptor, signature, exceptions)
    println("class = $mClassName, annotation = $classIsInject")
    return FastMethodVisitor(api, methodVisitor, access, methodName, descriptor, mClassName, classIsInject)
  }
}
