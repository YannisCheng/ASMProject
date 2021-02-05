package com.cwj.asm.cost_simple;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;



public class CostTimeAdviceAdapter extends AdviceAdapter {

    protected CostTimeAdviceAdapter(MethodVisitor mv, int access, String name, String desc) {
        super(ASM6, mv, access, name, desc);
    }

    @Override
    protected void onMethodEnter() {
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "com/cwj/asm/cost_simple/ComputeTargetCost", "startTime", "(Ljava/lang/String;)V", false);
    }

    @Override
    protected void onMethodExit(int opcode) {
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "com/cwj/asm/cost_simple/ComputeTargetCost", "stopTime", "(Ljava/lang/String;)V", false);
    }


}
