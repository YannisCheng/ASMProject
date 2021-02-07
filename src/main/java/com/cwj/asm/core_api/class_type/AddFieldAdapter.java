package com.cwj.asm.core_api.class_type;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 * ASM Core API Class 添加字段处理
 */
public class AddFieldAdapter extends ClassVisitor {
    private int fAcc;
    private String fName;
    private String fDesc;
    private boolean isFieldParent;

    public AddFieldAdapter() {
        super(ASM6);
    }

    public AddFieldAdapter(ClassVisitor cv, int mFAcc, String mFName, String mFDesc) {
        super(ASM6, cv);
        this.fAcc = mFAcc;
        this.fName = mFName;
        this.fDesc = mFDesc;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        super.visitField(access, name, desc, signature, value);
        if (name.equals(fName)) {
            isFieldParent = true;
        }
        return cv.visitField(access, name, desc, signature, value);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        if (!isFieldParent) {
            FieldVisitor fieldVisitor = cv.visitField(fAcc, fName, fDesc, null, null);
            if (fieldVisitor != null) {
                fieldVisitor.visitEnd();
            }
        }
        cv.visitEnd();
    }
}
