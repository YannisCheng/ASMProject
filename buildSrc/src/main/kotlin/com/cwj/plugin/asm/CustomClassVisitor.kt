package com.cwj.plugin.asm

import org.objectweb.asm.AnnotationVisitor
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

    var isIgnore = false;

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
            println("ClassVisitor className is : $name")
        }
    }

    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        println("visible is : $visible, descriptor is $descriptor")
        descriptor?.let {
            isIgnore = descriptor.equals("Lcom/cwj/myapplication/sdk/IgnoreTraceMethodCostClass;")
            println("isIgnore is $isIgnore")
        }
        return cv.visitAnnotation(descriptor, visible)
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        println("visitMethod methodName is : $name")
        var visitMethod = cv.visitMethod(access, name, descriptor, signature, exceptions)

        if (!isIgnore
            && !isInterface
            && visitMethod != null
            && !name.equals("<init>")
            && !name.equals("<clinit>")
            && name != null
            && descriptor != null
        ) {
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