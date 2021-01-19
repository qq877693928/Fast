package com.lizhenhua.fast.plugin.core

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Type
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

  private val mClassName: String?
  private val mMethodName: String?
  private var mShouldInject = false
  private val mDescriptor: String?
  private var mStartLabel: Label? = null
  private var mEndLabel: Label? = null

  // 方法的参数个数
  private val mParamSize: Int
  private var mLocalVariableIndex: Int
  private var mCursorVar = 0
  private var mStartTimeCursorVar = 0
  private val mParamTypes: MutableList<String> = ArrayList()
  override fun visitAnnotation(descriptor: String, visible: Boolean): AnnotationVisitor {
    if ("Lcom/lizhenhua/fast/annotation/FastLog;" == descriptor) {
      mShouldInject = true
    }
    return super.visitAnnotation(descriptor, visible)
  }

  public override fun onMethodEnter() {
    super.onMethodEnter()
    if (mShouldInject) {
      // 创建ArrayList
      mStartLabel = Label()
      mv.visitLabel(mStartLabel)
      mv.visitTypeInsn(NEW, Type.getInternalName(ArrayList::class.java))
      mv.visitInsn(DUP)
      mv.visitMethodInsn(
          INVOKESPECIAL,
          Type.getInternalName(ArrayList::class.java),
          "<init>",
          "()V",
          false
      )
      mv.visitVarInsn(ASTORE, mParamSize + 1)

      // List.add方法将参数值保留
      mCursorVar = 0
      for (i in 0 until mParamSize) {
        mv.visitLabel(Label())
        mv.visitVarInsn(ALOAD, mParamSize + 1)
        mv.visitVarInsn(ALOAD, ++mCursorVar)
        mv.visitMethodInsn(
            INVOKEINTERFACE,
            Type.getInternalName(List::class.java),
            "add",
            "(Ljava/lang/Object;)Z",
            true
        )
        mv.visitInsn(POP)
      }
      mv.visitLabel(Label())
      mv.visitLdcInsn(mClassName)
      mv.visitLdcInsn(mMethodName)
      mv.visitLdcInsn(mParamTypes.toString())
      mv.visitVarInsn(ALOAD, mParamSize + 1)
      mv.visitMethodInsn(
          INVOKESTATIC, "com/lizhenhua/fast/runtime/FastTraceLog", "enterMethod",
          "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;" +
              "Ljava/util/List;)V", false
      )

      //startTime
      mv.visitLabel(Label())
      mv.visitMethodInsn(
          INVOKESTATIC, Type.getInternalName(System::class.java), "currentTimeMillis", "()J",
          false
      )
      mStartTimeCursorVar = mParamSize + 1
      mv.visitVarInsn(LSTORE, mStartTimeCursorVar + 1)
      mEndLabel = Label()
      mv.visitLabel(mEndLabel)
      mv.visitLocalVariable(
          "objectList", Type.getDescriptor(ArrayList::class.java),
          "Ljava/util/ArrayList<Ljava/lang/Object;>;", mStartLabel, mEndLabel,
          ++mLocalVariableIndex
      )
      mv.visitLocalVariable(
          "startTime",
          "J",
          null,
          mStartLabel,
          mEndLabel,
          ++mLocalVariableIndex
      )
    }
  }

  public override fun onMethodExit(opcode: Int) {
    if (mShouldInject) {
      when (opcode) {
        RETURN -> {
        }
        ARETURN -> {
          mv.visitVarInsn(ASTORE, ++mLocalVariableIndex)
          mv.visitVarInsn(ALOAD, mLocalVariableIndex)
        }
      }
      val returnType = Type.getReturnType(mDescriptor)
      if (returnType !== Type.VOID_TYPE) {
        mEndLabel = Label()
        mv.visitLabel(mEndLabel)
        mv.visitLocalVariable(
            "returnObject", returnType.descriptor, null, mStartLabel,
            mEndLabel, mLocalVariableIndex
        )
      }
      mv.visitLabel(Label())
      mv.visitMethodInsn(
          INVOKESTATIC,
          Type.getInternalName(System::class.java),
          "currentTimeMillis",
          "()J",
          false
      )
      mv.visitVarInsn(LLOAD, mStartTimeCursorVar + 1)
      mv.visitInsn(LSUB)
      mv.visitVarInsn(LSTORE, mStartTimeCursorVar + 3)
      mv.visitLabel(Label())
      mv.visitLdcInsn(mClassName)
      mv.visitLdcInsn(mMethodName)
      if (Type.VOID_TYPE === returnType) {
        mv.visitInsn(ACONST_NULL)
      } else {
        mv.visitVarInsn(ALOAD, mLocalVariableIndex)
      }
      mv.visitVarInsn(LLOAD, mStartTimeCursorVar + 3)
      mv.visitInsn(if (Type.VOID_TYPE === returnType) ICONST_0 else ICONST_1)
      mv.visitMethodInsn(
          INVOKESTATIC, "com/lizhenhua/fast/runtime/FastTraceLog", "exitMethod",
          "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;JZ)V", false
      )
    }
    super.onMethodExit(opcode)
  }

  init {
    mClassName = className.replace('/', '.')
    mMethodName = methodName
    mDescriptor = descriptor
    val argTypes: Array<Type> = Type.getArgumentTypes(mDescriptor)
    argTypes.forEach { type -> mParamTypes.add(type.className) }
    mParamSize = argTypes.size
    mLocalVariableIndex = mParamSize
  }
}