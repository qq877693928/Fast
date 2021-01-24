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

  // 方法的参数个数
  private val mParamSize: Int
  private var mTimeLocalIndex = 0
  private var mParamsLocalIndex = 0
  private val mParamTypes: MutableList<String> = ArrayList()
  private var mArgumentTypes: Array<Type>? = null
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
      mParamsLocalIndex = mParamSize + 1
      mv.visitVarInsn(ASTORE, mParamsLocalIndex)

      // List.add方法将参数值保留
      for ((cursorVar, i) in (0 until mParamSize).withIndex()) {
        mv.visitLabel(Label())
        mv.visitVarInsn(ALOAD, mParamsLocalIndex)
        mv.visitVarInsn(ALOAD, cursorVar + 1)
        mArgumentTypes?.get(i)?.let { visitParamsValue(it) }
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
      mv.visitVarInsn(ALOAD, mParamsLocalIndex)
      mv.visitMethodInsn(
          INVOKESTATIC,
          "com/lizhenhua/fast/runtime/FastTraceLog",
          "enterMethod",
          "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V",
          false
      )

      //startTime
      mv.visitLabel(Label())
      mv.visitMethodInsn(
          INVOKESTATIC,
          Type.getInternalName(System::class.java),
          "currentTimeMillis",
          "()J",
          false
      )
      mTimeLocalIndex = newLocal(Type.LONG_TYPE)
      mv.visitVarInsn(LSTORE, mTimeLocalIndex)
    }
  }

  private fun visitParamsValue(type: Type) {
    when (type.sort) {
      Type.BOOLEAN -> {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
        mParamTypes.add("java.lang.Boolean")
      }
      Type.INT -> {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        mParamTypes.add("java.lang.Integer")
      }
      Type.BYTE -> {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;", false);
        mParamTypes.add("java.lang.Byte")
      }
      Type.SHORT -> {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;", false);
        mParamTypes.add("java.lang.Short")
      }
      Type.LONG -> {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
        mParamTypes.add("java.lang.Long")
      }
      Type.FLOAT -> {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;", false);
        mParamTypes.add("java.lang.Float")
      }
      Type.DOUBLE -> {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false);
        mParamTypes.add("java.lang.Double")
      }
      Type.CHAR -> {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;", false);
        mParamTypes.add("java.lang.Character")
      }
      else -> {
        mParamTypes.add(type.className)
      }
    }
  }

  public override fun onMethodExit(opcode: Int) {
    if (mShouldInject && ATHROW != opcode) {
      val returnType = Type.getReturnType(mDescriptor)
      val nextLocal: Int = this.nextLocal
      var returnResultStoreOpcode: Int = NOP
      var returnResultLoadOpcode: Int = NOP

      if (opcode in IRETURN..ARETURN) {
        when (opcode) {
          IRETURN -> {
            returnResultStoreOpcode = ISTORE
            returnResultLoadOpcode = ILOAD
          }
          LRETURN -> {
            returnResultStoreOpcode = LSTORE
            returnResultLoadOpcode = LLOAD
          }
          FRETURN -> {
            returnResultStoreOpcode = FSTORE
            returnResultLoadOpcode = FLOAD
          }
          DRETURN -> {
            returnResultStoreOpcode = DSTORE
            returnResultLoadOpcode = DLOAD
          }
          ARETURN -> {
            returnResultStoreOpcode = ASTORE
            returnResultLoadOpcode = ALOAD
          }
        }
        mv.visitVarInsn(returnResultStoreOpcode, nextLocal)
        mv.visitVarInsn(returnResultLoadOpcode, nextLocal)
      }

      mv.visitLabel(Label())
      mv.visitMethodInsn(
          INVOKESTATIC,
          Type.getInternalName(System::class.java),
          "currentTimeMillis",
          "()J",
          false
      )
      mv.visitVarInsn(LLOAD, mTimeLocalIndex)
      mv.visitInsn(LSUB)
      mv.visitVarInsn(LSTORE, mTimeLocalIndex)
      mv.visitLabel(Label())
      mv.visitLdcInsn(mClassName)
      mv.visitLdcInsn(mMethodName)
      if (Type.VOID_TYPE === returnType) {
        mv.visitInsn(ACONST_NULL)
      } else {
        if (opcode in IRETURN..ARETURN) {
          mv.visitVarInsn(returnResultLoadOpcode, nextLocal)
        }
      }
      mv.visitVarInsn(LLOAD, mTimeLocalIndex)
      mv.visitInsn(if (Type.VOID_TYPE === returnType) ICONST_0 else ICONST_1)
      if (opcode in IRETURN..DRETURN) {
        mv.visitMethodInsn(
            INVOKESTATIC, "com/lizhenhua/fast/runtime/FastTraceLog", "exitMethod",
            "(Ljava/lang/String;Ljava/lang/String;" + returnType.descriptor + "JZ)V", false
        )
      } else {
        mv.visitMethodInsn(
            INVOKESTATIC, "com/lizhenhua/fast/runtime/FastTraceLog", "exitMethod",
            "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;JZ)V", false
        )
      }
    }
    super.onMethodExit(opcode)
  }

  init {
    mClassName = className.replace('/', '.')
    mMethodName = methodName
    mDescriptor = descriptor
    mArgumentTypes = Type.getArgumentTypes(mDescriptor)
    mParamSize = (mArgumentTypes as Array<out Type>?)?.size ?: 0
  }
}