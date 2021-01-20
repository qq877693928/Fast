package com.lizhenhua.fast.plugin.core

import com.lizhenhua.fast.plugin.FastExtension
import com.lizhenhua.fast.plugin.base.AbstractTransform
import org.gradle.api.Project
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes
import java.io.File

internal class FastTransformer(private val project: Project) : AbstractTransform() {
  override fun getName(): String {
    return TAG
  }

  override fun getClassVisitor(classVisitor: ClassVisitor): ClassVisitor {
    return FastClassVisitor(Opcodes.ASM7, classVisitor)
  }

  override fun enableTraceClass(inputFile: File): Boolean {
    var fastExtension: FastExtension = project.extensions.getByType(FastExtension::class.java)

    val name = inputFile.name
    return fastExtension.enable && !(!name.endsWith(".class")
        || name.startsWith("R.class")
        || name.startsWith("R$")
        || "BuildConfig.class" === name)
  }

  companion object {
    private const val TAG = "FastTransformer"
  }
}
