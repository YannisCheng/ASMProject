package com.cwj.asm.core_api.method_type.cost_simple;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/**
 * ClassComputeCostAdapter  ASM类访问器
 *
 * @author wenjia.Cheng  cwj1714@163.com
 * @date 2021/2/5 14:18
 */
public class ClassComputeCostAdapter extends ClassVisitor {

    private boolean isInterface;
    private String className = "";
    private String localVar = "c223Name3434";

    public ClassComputeCostAdapter(ClassVisitor cv) {
        super(ASM6, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        // 注意： 不要调用super()
        // super.visit(version, access, name, signature, superName, interfaces);
        // owner is : com/cwj/asm/method_type/TargetTest
        isInterface = (access & ACC_INTERFACE) != 0;
        className = name;
        cv.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        // 注意： 不要调用super()
        //super.visitMethod(access, name, desc, signature, exceptions);
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if (mv != null && !isInterface && !name.equals("<init>") && !name.equals("<cinit>")) {
            // 第1版，直接使用MethodVisitor进行处理
            //mv = new MethodCostAdapter(mv);
            // 第2版，直接使用AdviceAdapter进行处理
            mv = new MethodCostAdapter(mv, className, name,localVar);
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        //super.visitEnd();
        cv.visitField(ACC_PRIVATE, localVar, "Ljava/lang/String;", null, className);
        /*if (fieldVisitor != null) {
            fieldVisitor.visitEnd();
        }
        cv.visitEnd();*/
    }
}
