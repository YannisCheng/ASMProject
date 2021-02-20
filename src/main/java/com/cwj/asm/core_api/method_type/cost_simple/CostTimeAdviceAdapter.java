package com.cwj.asm.core_api.method_type.cost_simple;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;



public class CostTimeAdviceAdapter extends AdviceAdapter {

    protected CostTimeAdviceAdapter(MethodVisitor mv, int access, String name, String desc) {
        super(ASM6, mv, access, name, desc);
    }

    @Override
    protected void onMethodEnter() {
        mv.visitMethodInsn(INVOKESTATIC, "com/cwj/asm/core_api/method_type/cost_simple/ComputeTargetCost", "startTime", "()V", false);
    }

    @Override
    protected void onMethodExit(int opcode) {
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD,"com/cwj/asm/core_api/method_type/cost_simple/TargetTest","classN","Ljava/lang/String;");
        mv.visitVarInsn(ALOAD,0);
        mv.visitFieldInsn(GETFIELD, "com/cwj/asm/core_api/method_type/cost_simple/TargetTest","methodN","Ljava/lang/String;");
        mv.visitMethodInsn(INVOKESTATIC, "com/cwj/asm/core_api/method_type/cost_simple/ComputeTargetCost", "stopTime", "(Ljava/lang/String;Ljava/lang/String;)V", false);
    }


}
