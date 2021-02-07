package com.cwj.asm.core_api.metadata_type.annotation.visits;

import com.cwj.asm.utils.LogShowJ;
import org.objectweb.asm.AnnotationVisitor;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 * AnnotaCustom  自定义ASM-AnnotationVisitor
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/7 14:14
*/
public class AnnotaCustom extends AnnotationVisitor {

    private static final String TAG = "\n    ----> AnnotationVisitor ";

    public AnnotaCustom(AnnotationVisitor av) {
        super(ASM6, av);
    }

    @Override
    public void visit(String name, Object value) {
        //super.visit(name, value);
        LogShowJ.showLog(TAG
                        + "visit "
                        + String.format("name is :%s, value is :%s", name, value),
                true);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String name, String desc) {
        //super.visitAnnotation(name, desc);
        LogShowJ.showLog(TAG
                        + "visitAnnotation "
                        + String.format("name is :%s, desc is :%s", name, desc),
                true);
        return av.visitAnnotation(name, desc);
    }

    @Override
    public AnnotationVisitor visitArray(String name) {
        //super.visitArray(name);
        LogShowJ.showLog(TAG
                        + "visitArray "
                        + String.format("name is :%s", name),
                true);
        return av.visitArray(name);
    }

    @Override
    public void visitEnum(String name, String desc, String value) {
        //super.visitEnum(name, desc, value);
        LogShowJ.showLog(TAG
                        + "visitEnum "
                        + String.format("name=%s, desc=%s, value=%s", name, desc, value),
                true);
    }

    @Override
    public void visitEnd() {
        //super.visitEnd();
        LogShowJ.showLog(TAG + "visitEnd----", true);
        System.out.println();
    }
}
