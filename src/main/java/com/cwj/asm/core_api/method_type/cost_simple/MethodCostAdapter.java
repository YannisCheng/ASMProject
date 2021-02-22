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

    private String tempName = "";
    private String methodName = "";
    private String localVar = "";

    public MethodCostAdapter(MethodVisitor mv, String className, String name, String varName) {
        super(ASM6, mv);
        this.tempName = className;
        this.methodName = name;
        this.localVar = varName;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        // 为局部变量 赋值
        mv.visitVarInsn(ALOAD,0);
        mv.visitLdcInsn(tempName+"&"+methodName);
        mv.visitFieldInsn(PUTFIELD,tempName,localVar,"Ljava/lang/String;");
        // 获取指定的局部 的值
        mv.visitVarInsn(ALOAD,0);
        mv.visitFieldInsn(GETFIELD,tempName,localVar,"Ljava/lang/String;");
        mv.visitMethodInsn(INVOKESTATIC, "com/cwj/asm/core_api/method_type/cost_simple/ComputeTargetCost", "startTime", "(Ljava/lang/String;)V", false);
    }


    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
            // 获取指定的局部 的值
            mv.visitVarInsn(ALOAD,0);
            mv.visitFieldInsn(GETFIELD,tempName,localVar,"Ljava/lang/String;");
            mv.visitMethodInsn(INVOKESTATIC, "com/cwj/asm/core_api/method_type/cost_simple/ComputeTargetCost", "stopTime", "(Ljava/lang/String;)V", false);
        }
        mv.visitInsn(opcode);
    }
}
