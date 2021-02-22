package com.cwj.asm.core_api.method_type.cost_simple;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;



public class CostTimeAdviceAdapter extends AdviceAdapter {

    private String tempName = "";
    private String methodName = "";
    protected CostTimeAdviceAdapter(MethodVisitor mv, int access, String className, String name, String desc) {
        super(ASM6, mv, access, name, desc);
        tempName = className;
        methodName = name;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitVarInsn(ALOAD,0);
        mv.visitLdcInsn(tempName+"&"+methodName);
        mv.visitFieldInsn(PUTFIELD,tempName,"c223Name3434","Ljava/lang/String;");
    }

    @Override
    protected void onMethodEnter() {
        mv.visitVarInsn(ALOAD,0);
        mv.visitFieldInsn(GETFIELD,tempName,"c223Name3434","Ljava/lang/String;");
        mv.visitMethodInsn(INVOKESTATIC, "com/cwj/asm/core_api/method_type/cost_simple/ComputeTargetCost", "startTime", "(Ljava/lang/String;)V", false);
    }

    @Override
    protected void onMethodExit(int opcode) {
        mv.visitVarInsn(ALOAD,0);
        mv.visitFieldInsn(GETFIELD,tempName,"c223Name3434","Ljava/lang/String;");
        mv.visitMethodInsn(INVOKESTATIC, "com/cwj/asm/core_api/method_type/cost_simple/ComputeTargetCost", "stopTime", "(Ljava/lang/String;)V", false);
    }


}
