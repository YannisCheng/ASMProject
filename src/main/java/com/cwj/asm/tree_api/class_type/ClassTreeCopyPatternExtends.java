package com.cwj.asm.tree_api.class_type;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.tree.ClassNode;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 * ClassTreeCopyPatternExtends  转换类模式1：继承
 *
 * @author wenjia.Cheng  cwj1714@163.com
 * @date 2021/2/8 11:17
 */
public class ClassTreeCopyPatternExtends extends ClassNode {

    public ClassTreeCopyPatternExtends(ClassVisitor classVisitor) {
        super(ASM6);
        this.cv = classVisitor;
    }

    @Override
    public void visitEnd() {
        //super.visitEnd();
        accept(cv);
    }
}
