package com.lizhenhua.fast.plugin.info

import com.lizhenhua.fast.plugin.util.ParamUtil.appendParamType
import org.objectweb.asm.Type
import java.util.*

class MethodModel(
    private val mClassName: String,
    private val mAccess: Int,
    private val mMethodName: String,
    private val mDescriptor: String
) {
    val className: String = mClassName
    val access: Int = mAccess
    val methodName: String = mMethodName
    val descriptor: String = mDescriptor

    /**
     * 是否需要插入FastLog代码
     */
    var isInject = false

    /**
     * 方法入参个数
     */
    var paramSize = 0

    /**
     * 进入方法时间
     */
    var timeLocalIndex = 0

    /**
     * 入参类型名称列表
     */
    var paramClassNameList: List<String> = ArrayList()

    /**
     * 入参类型列表
     */
    var paramTypeList: Array<Type>? = null


    override fun toString(): String {
        return "MethodModel{" +
                "className='" + mClassName + '\'' +
                ", access=" + mAccess +
                ", methodName='" + mMethodName + '\'' +
                ", descriptor='" + mDescriptor + '\'' +
                ", inject=" + isInject +
                ", paramSize=" + paramSize +
                ", timeLocalIndex=" + timeLocalIndex +
                ", paramClassNameList=" + paramClassNameList +
                ", paramTypeList=" + paramTypeList +
                '}'
    }

    companion object {
        @JvmStatic
        fun parse(
            className: String,
            access: Int,
            methodName: String,
            descriptor: String
        ): MethodModel {
            val classNameString = className.replace('/', '.')
            val accessValue = 0.coerceAtLeast(access)
            val types: Array<Type> = Type.getArgumentTypes(descriptor)
            val paramSize = types.size
            val paramClassNameList: MutableList<String> = ArrayList()
            for (type in types) {
                appendParamType(type, paramClassNameList)
            }
            val methodModel = MethodModel(classNameString, accessValue, methodName, descriptor)
            methodModel.paramSize = paramSize
            methodModel.paramTypeList = types
            methodModel.paramClassNameList = paramClassNameList
            return methodModel
        }
    }
}