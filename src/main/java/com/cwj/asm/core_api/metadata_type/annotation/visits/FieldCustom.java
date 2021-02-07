package com.cwj.asm.core_api.metadata_type.annotation.visits;

import com.cwj.asm.utils.LogShowJ;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.TypePath;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 * FieldCustom  自定义ASM-FieldVisitor
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/7 14:13
*/
public class FieldCustom extends FieldVisitor {

    private static final String TAG = "\n    ----> FieldVisitor ";

    public FieldCustom() {
        super(ASM6);
    }

    public FieldCustom(FieldVisitor fv) {
        super(ASM6, fv);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
        super.visitTypeAnnotation(typeRef, typePath, desc, visible);
        LogShowJ.showLog(TAG
                        + "visitTypeAnnotation "
                        + String.format("typeRef=%d, typePath=%s, desc=%s, visible=%b", typeRef, typePath.toString(), desc, visible),
                true);
        AnnotationVisitor annotation = fv.visitTypeAnnotation(typeRef, typePath, desc, visible);
        if (annotation != null) {
            annotation = new AnnotaCustom(annotation);
        }
        return annotation;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        super.visitAnnotation(desc, visible);
        LogShowJ.showLog(TAG
                        + "visitAnnotation "
                        + String.format("desc=%s, visible=%b", desc, visible),
                true);
        AnnotationVisitor annotation = fv.visitAnnotation(desc, visible);
        if (annotation != null) {
            annotation = new AnnotaCustom(annotation);
        }
        return annotation;
    }

    @Override
    public void visitAttribute(Attribute attr) {
        super.visitAttribute(attr);
        LogShowJ.showLog(TAG
                        + "visitAttribute "
                        + String.format("attr=%s", attr.toString()),
                true);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        LogShowJ.showLog(TAG + "visitEnd----", true);
        System.out.println();
    }

}
