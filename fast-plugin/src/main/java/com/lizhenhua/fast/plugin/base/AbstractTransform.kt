package com.lizhenhua.fast.plugin.base

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import kotlinx.coroutines.*
import org.apache.commons.io.FileUtils
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import java.io.File
import java.io.FileOutputStream

abstract class AbstractTransform : Transform() {
    private lateinit var outputProvider: TransformOutputProvider

    abstract override fun getName(): String

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    override fun isIncremental(): Boolean = false

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    override fun transform(transformInvocation: TransformInvocation?) {
        super.transform(transformInvocation)

        //TransformOutputProvider管理输出路径,如果消费型输入为空,则outputProvider也为空
        outputProvider = transformInvocation?.outputProvider!!

        if (!transformInvocation.isIncremental) {
            outputProvider.deleteAll()
        }
        processTransform(transformInvocation)
    }

    private fun processTransform(transformInvocation: TransformInvocation) {
        runBlocking {
            var deferreds: ArrayList<Deferred<Unit>> = ArrayList()
            transformInvocation.inputs.forEach { transformInput ->
                transformInput.directoryInputs.forEach { directoryInput ->
                    val thread = async(Dispatchers.IO) {
                        processDirectoryInput(directoryInput, transformInvocation.isIncremental)
                    }
                    deferreds.add(thread)
                }

                transformInput.jarInputs.forEach { jarInput ->
                    val thread = async(Dispatchers.IO) {
                        processJarInput(
                            jarInput,
                            transformInvocation.isIncremental
                        )
                    }
                    deferreds.add(thread)
                }
            }
            deferreds.awaitAll()
        }
    }

    /**
     * 处理源码文件
     *
     * <p>将修改的字节码拷贝到指定目录下，实现编译期干预字节码<p/>
     *
     * @param directoryInput 待处理的源码文件
     */
    private fun processDirectoryInput(directoryInput: DirectoryInput, incremental: Boolean) {
        val dest: File = outputProvider.getContentLocation(
            directoryInput.name,
            directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY
        )
        FileUtils.forceMkdir(dest)

        if (incremental) {
            directoryInput.changedFiles.forEach { changedFile ->
                val status: Status = changedFile.value
                val inputFile: File = changedFile.key
                val destFilePath: String = inputFile.absolutePath.replace(
                    directoryInput.file.absolutePath,
                    dest.absolutePath
                )
                val destFile = File(destFilePath)
                when (status) {
                    Status.NOTCHANGED -> {
                    }
                    Status.ADDED, Status.CHANGED -> {
                        FileUtils.touch(destFile)
                        transformSingleFile(inputFile, destFile)
                    }
                    Status.REMOVED -> {
                        if (destFile.exists()) {
                            FileUtils.forceDelete(destFile)
                        }
                    }
                }
            }
        } else {
            transformDirectory(directoryInput, dest)
        }
    }

    private fun transformDirectory(directoryInput: DirectoryInput, dest: File) {
        if (directoryInput.file.isDirectory) {
            val extensions: Array<String> = arrayOf("class")
            FileUtils.listFiles(directoryInput.file, extensions, true).forEach { inputFile ->
                val destFilePath: String = inputFile.absolutePath.replace(
                    directoryInput.file
                        .absolutePath, dest.absolutePath
                )
                val destFile = File(destFilePath)
                FileUtils.touch(destFile)
                transformSingleFile(inputFile, destFile)
            }
        }
    }

    private fun transformSingleFile(inputFile: File, dest: File) {
        if (enableTraceClass(inputFile)) {
            val classReader = ClassReader(inputFile.readBytes())
            val classWriter = ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
            classReader.accept(getClassVisitor(classWriter), ClassReader.EXPAND_FRAMES)
            val code: ByteArray = classWriter.toByteArray()
            val fos = FileOutputStream(dest)
            fos.write(code)
            fos.close()
        } else {
            FileUtils.copyFile(inputFile, dest)
        }
    }

    /**
     * 处理Jar文件
     *
     * <p>将修改的字节码拷贝到指定目录下，实现编译期干预字节码<p/>
     *
     * @param jarInput 待处理的Jar文件
     */
    private fun processJarInput(jarInput: JarInput, incremental: Boolean) {
        val dest: File = outputProvider.getContentLocation(
            jarInput.file.absolutePath,
            jarInput.contentTypes, jarInput.scopes, Format.JAR
        )
        val status: Status = jarInput.status
        if (incremental) {
            when (status) {
                Status.NOTCHANGED -> {
                }
                Status.ADDED, Status.CHANGED -> {
                    FileUtils.copyFile(jarInput.file, dest)
                }
                Status.REMOVED -> {
                    if (dest.exists()) {
                        FileUtils.forceDelete(dest);
                    }
                }
            }
        } else {
            FileUtils.copyFile(jarInput.file, dest)
        }
    }

    /**
     * 获取插桩的ClassVisitor
     * @param classVisitor
     * @return {@see ClassVisitor}
     */
    protected abstract fun getClassVisitor(classVisitor: ClassVisitor): ClassVisitor

    /**
     * 判断该文件是否需要插桩
     * @param inputFile 文件对象
     * @return true表示需要插桩，false表示不需要插桩
     */
    protected abstract fun enableTraceClass(inputFile: File): Boolean
}