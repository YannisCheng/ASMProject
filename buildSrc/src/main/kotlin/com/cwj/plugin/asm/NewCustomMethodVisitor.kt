package com.cwj.plugin.asm

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

    override fun onMethodEnter() {
        mv.visitMethodInsn(
            INVOKESTATIC,
            "com/cwj/myapplication/sdk/ComputeTargetCost",
            "startTime",
            "()V",
            false
        )
    }

    override fun onMethodExit(opcode: Int) {
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