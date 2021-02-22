package com.cwj.plugin.asm

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*


/**
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/20
 */
class NewCustomMethodVisitor(
    mv: MethodVisitor,
    val methodName: String,
    val className: String,
    val localVar: String
) : MethodVisitor(ASM6, mv) {

    var isIgnore = false;

    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        println("visible is : $visible, descriptor is $descriptor")
        descriptor?.let {
            isIgnore = descriptor.equals("Lcom/cwj/myapplication/sdk/IgnoreTraceMethodCostMethod;")
            println("isIgnore is $isIgnore")
        }
        return mv.visitAnnotation(descriptor, visible)
    }

    override fun visitCode() {
        super.visitCode()
        if (!isIgnore) {
            // 为局部变量 赋值
            mv.visitVarInsn(ALOAD, 0)
            mv.visitLdcInsn(className + "&" + methodName)
            mv.visitFieldInsn(PUTFIELD, className, localVar, "Ljava/lang/String;")
            // 获取指定的局部 的值
            mv.visitVarInsn(ALOAD, 0)
            mv.visitFieldInsn(GETFIELD, className, localVar, "Ljava/lang/String;")
            mv.visitMethodInsn(
                INVOKESTATIC,
                "com/cwj/myapplication/sdk/ComputeTargetCost",
                "startTime",
                "(Ljava/lang/String;)V",
                false
            )
        }
    }


    override fun visitInsn(opcode: Int) {
        if (!isIgnore) {
            if (opcode >= IRETURN && opcode <= RETURN || opcode == ATHROW) {
                // 获取指定的局部 的值
                mv.visitVarInsn(ALOAD, 0)
                mv.visitFieldInsn(GETFIELD, className, localVar, "Ljava/lang/String;")
                mv.visitMethodInsn(
                    INVOKESTATIC,
                    "com/cwj/myapplication/sdk/ComputeTargetCost",
                    "stopTime",
                    "(Ljava/lang/String;)V",
                    false
                )
            }
        }
        mv.visitInsn(opcode)
    }
}