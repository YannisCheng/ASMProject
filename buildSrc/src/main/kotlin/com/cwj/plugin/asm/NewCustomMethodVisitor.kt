package com.cwj.plugin.asm

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

/**
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/20
 */
class NewCustomMethodVisitor(
    mv: MethodVisitor,
    access: Int,
    name: String,
    descriptor: String,
    val className: String
) : AdviceAdapter(ASM6, mv, access, name, descriptor) {

    var isIgnore = false;

    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        println("visible is : $visible, descriptor is $descriptor")
        descriptor?.let {
            isIgnore = descriptor.equals("Lcom/cwj/myapplication/sdk/IgnoreTraceMethodCostMethod;")
            println("isIgnore is $isIgnore")
        }
        return mv.visitAnnotation(descriptor, visible)
    }

    override fun onMethodEnter() {
        if (!isIgnore) {
            mv.visitMethodInsn(
                INVOKESTATIC,
                "com/cwj/myapplication/sdk/ComputeTargetCost",
                "startTime",
                "()V",
                false
            )
        }

    }

    override fun onMethodExit(opcode: Int) {
        if (!isIgnore) {
            mv.visitVarInsn(ALOAD, 0)
            mv.visitFieldInsn(
                GETFIELD,
                "com/cwj/plugin/asm/CustomMethodVisitor",
                "className",
                "Ljava/lang/String;"
            )
            mv.visitVarInsn(ALOAD, 0)
            mv.visitFieldInsn(
                GETFIELD,
                "com/cwj/plugin/asm/CustomMethodVisitor",
                "name",
                "Ljava/lang/String;"
            )
            mv.visitMethodInsn(
                INVOKESTATIC,
                "com/cwj/myapplication/sdk/ComputeTargetCost",
                "stopTime",
                "(Ljava/lang/String;Ljava/lang/String;)V",
                false
            )
        }
    }
}