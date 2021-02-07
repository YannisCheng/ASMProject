package com.cwj.asm.metadata_type.annotation.visits;

import com.cwj.asm.utils.LogShowJ;
import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 * MethodCustom  自定义ASM-MethodVisitor
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/7 14:13
*/
public class MethodCustom extends MethodVisitor {

    private static final String TAG = "\n    ----> MethodVisitor ";

    public MethodCustom(MethodVisitor mv) {
        super(ASM6, mv);
    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
    }

    @Override
    public void visitCode() {
        super.visitCode();
    }

    @Override
    public void visitAttribute(Attribute attr) {
        super.visitAttribute(attr);
    }


    // start ---- AnnotationVisitor ----
    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        super.visitAnnotation(desc, visible);
        LogShowJ.showLog(TAG
                        + "visitAnnotation "
                        + String.format("desc=%s, visible=%b", desc, visible),
                true);
        AnnotationVisitor annotation = mv.visitAnnotation(desc, visible);
        if (annotation != null) {
            annotation = new AnnotaCustom(annotation);
        }
        return annotation;
    }

    @Override
    public AnnotationVisitor visitAnnotationDefault() {
        super.visitAnnotationDefault();
        LogShowJ.showLog(TAG, true);
        AnnotationVisitor annotationDefault = mv.visitAnnotationDefault();
        if (annotationDefault != null) {
            annotationDefault = new AnnotaCustom(annotationDefault);
        }
        return annotationDefault;
    }

    @Override
    public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
        super.visitInsnAnnotation(typeRef, typePath, desc, visible);
        LogShowJ.showLog(TAG
                        + "visitInsnAnnotation "
                        + String.format("typeRef=%d, typePath=%s, desc=%s, visible=%d", typeRef, typePath.toString(), desc, visible),
                true);
        AnnotationVisitor annotation = mv.visitInsnAnnotation(typeRef, typePath, desc, visible);
        if (annotation != null) {
            annotation = new AnnotaCustom(annotation);
        }
        return annotation;
    }

    @Override
    public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String desc, boolean visible) {
        super.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, desc, visible);
        LogShowJ.showLog(TAG
                        + "visitLocalVariableAnnotation "
                        + String.format("typeRef=%d, typePath=%s, desc=%s, visible=%b", typeRef, typePath.toString(), desc, visible),
                true);
        AnnotationVisitor annotation = mv.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, desc, visible);
        if (annotation != null) {
            annotation = new AnnotaCustom(annotation);
        }
        return annotation;
    }

    @Override
    public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
        super.visitParameterAnnotation(parameter, desc, visible);
        LogShowJ.showLog(TAG
                        + "visitParameterAnnotation "
                        + String.format("parameter=%d, desc=%s, visible=%b", parameter, desc, visible),
                true);
        AnnotationVisitor annotation = mv.visitParameterAnnotation(parameter, desc, visible);
        if (annotation != null) {
            annotation = new AnnotaCustom(annotation);
        }
        return annotation;
    }

    @Override
    public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
        super.visitTryCatchAnnotation(typeRef, typePath, desc, visible);
        AnnotationVisitor annotation = mv.visitTryCatchAnnotation(typeRef, typePath, desc, visible);
        if (annotation != null) {
            annotation = new AnnotaCustom(annotation);
        }
        return annotation;
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
        super.visitTypeAnnotation(typeRef, typePath, desc, visible);
        LogShowJ.showLog(TAG
                        + "visitTypeAnnotation "
                        + String.format("typeRef=%d, typePath=%s, desc=%s, visible=%b", typeRef, typePath.toString(), desc, visible),
                true);
        AnnotationVisitor annotation = mv.visitTypeAnnotation(typeRef, typePath, desc, visible);
        if (annotation != null) {
            annotation = new AnnotaCustom(annotation);
        }
        return annotation;
    }
    // end ---- AnnotationVisitor ----

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        super.visitFieldInsn(opcode, owner, name, desc);

    }

    @Override
    public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
        super.visitFrame(type, nLocal, local, nStack, stack);
    }

    @Override
    public void visitIincInsn(int var, int increment) {
        super.visitIincInsn(var, increment);
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        super.visitIntInsn(opcode, operand);
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        super.visitJumpInsn(opcode, label);
    }

    @Override
    public void visitLabel(Label label) {
        super.visitLabel(label);
    }

    @Override
    public void visitLdcInsn(Object cst) {
        super.visitLdcInsn(cst);
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        super.visitLineNumber(line, start);
    }

    @Override
    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
        super.visitLocalVariable(name, desc, signature, start, end, index);
    }

    @Override
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        super.visitLookupSwitchInsn(dflt, keys, labels);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack, maxLocals);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }

    @Override
    public void visitMultiANewArrayInsn(String desc, int dims) {
        super.visitMultiANewArrayInsn(desc, dims);
    }

    @Override
    public void visitParameter(String name, int access) {
        super.visitParameter(name, access);
    }

    @Override
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        super.visitTableSwitchInsn(min, max, dflt, labels);
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        super.visitTryCatchBlock(start, end, handler, type);
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        super.visitTypeInsn(opcode, type);
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        super.visitVarInsn(opcode, var);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        LogShowJ.showLog(TAG + "visitEnd----", true);
        System.out.println();
    }

}
