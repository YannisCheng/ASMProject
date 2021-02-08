package com.cwj.asm.tree_api.class_type;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 * ClassTreeCopyPatternDelegation  转换类模式2：委托
 *
 * @author wenjia.Cheng  cwj1714@163.com
 * @date 2021/2/8 11:16
 */
public class ClassTreeCopyPatternDelegation extends ClassVisitor {

    private final ClassVisitor next;

    public ClassTreeCopyPatternDelegation(ClassVisitor cv) {
        super(ASM6, cv);
        next = cv;
    }

    @Override
    public void visitEnd() {
        //super.visitEnd();
        ClassNode classNode = (ClassNode) cv;
        classNode.accept(next);
    }
}
