package com.cwj.asm.core_api.method_type.cost_simple;


import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;



public class CostTimeAdviceAdapter extends AdviceAdapter {

    protected CostTimeAdviceAdapter(MethodVisitor mv, int access, String name, String desc) {
        super(ASM6, mv, access, name, desc);
    }

    @Override
    protected void onMethodEnter() {

        mv.visitLdcInsn("ComputeTargetCost&targetMethod");
        // 注意aload_0一般为当前类的this指针
        mv.visitVarInsn(ASTORE,0);
        mv.visitVarInsn(ALOAD,0);
        mv.visitMethodInsn(INVOKESTATIC, "com/cwj/asm/core_api/method_type/cost_simple/ComputeTargetCost", "startTime", "(Ljava/lang/String;)V", false);
    }

    @Override
    protected void onMethodExit(int opcode) {
        mv.visitVarInsn(ALOAD,0);
        mv.visitMethodInsn(INVOKESTATIC, "com/cwj/asm/core_api/method_type/cost_simple/ComputeTargetCost", "stopTime", "(Ljava/lang/String;)V", false);
    }


}
