package com.cwj.plugin.asm

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.ACC_INTERFACE
import org.objectweb.asm.Opcodes.ASM6

/**
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/19
 */
class CustomClassVisitor(cv: ClassVisitor) : ClassVisitor(ASM6, cv) {

    var isInterface: Boolean = false
    var className: String = "null"

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        isInterface = (access and ACC_INTERFACE) != 0
        cv.visit(version, access, name, signature, superName, interfaces)
        name?.let {
            className = name
        }
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        var visitMethod = cv.visitMethod(access, name, descriptor, signature, exceptions)
        if (!isInterface && visitMethod != null && !name.equals("<init>") && !name.equals("<clinit>") && name != null && descriptor != null) {
            visitMethod = NewCustomMethodVisitor(
                visitMethod,
                access,
                name,
                descriptor,
                className
            )
        }
        return visitMethod
    }
}