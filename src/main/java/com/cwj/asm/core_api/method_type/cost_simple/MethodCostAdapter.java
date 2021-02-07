package com.cwj.asm.core_api.method_type.cost_simple;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/**
 * MethodCostAdapter  ASM方法访问器
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/5 14:18
*/
public class MethodCostAdapter extends MethodVisitor {

    public MethodCostAdapter(MethodVisitor mv) {
        super(ASM6, mv);
    }

    @Override
    public void visitCode() {
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "com/cwj/asm/core_api/method_type/cost_simple/ComputeTargetCost", "startTime", "(Ljava/lang/String;)V", false);
    }


    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "com/cwj/asm/core_api/method_type/cost_simple/ComputeTargetCost", "stopTime", "(Ljava/lang/String;)V", false);
        }
        mv.visitInsn(opcode);
    }
}
