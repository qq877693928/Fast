package com.lizhenhua.fast.plugin.strategy

import org.objectweb.asm.MethodVisitor

interface IMethod {
    fun visitAnnotation(descriptor: String)
    fun visitMethodEnter(mv: MethodVisitor?)
    fun visitMethodExit(nextLocal: Int, mv: MethodVisitor?, opcode: Int)
}