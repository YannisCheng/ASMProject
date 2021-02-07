package com.cwj.asm.metadata_type.annotation.visits;

import com.cwj.asm.utils.LogShowJ;
import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 * ClassCustom  自定义ASM-ClassVisitor
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/7 14:13
*/
public class ClassCustom extends ClassVisitor {

    private static final String TAG = "ClassVisitor ";

    public ClassCustom(ClassVisitor cv) {
        super(ASM6, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        //super.visit(version, access, name, signature, superName, interfaces);
        LogShowJ.showLog(TAG
                        + "visit ----> ",
                true);

        System.out.printf("    version=%d, access=%d, name=%s, signature=%s, superName=%s interfaces size=%d%n", version, access, name, signature, superName, interfaces.length);
        for (int i = 0; i < interfaces.length; i++) {
            System.out.printf("    interfaces[%d]=%s%n", i, interfaces[i]);
        }
        System.out.println();
    }

    @Override
    public void visitSource(String source, String debug) {
        //super.visitSource(source, debug);
        LogShowJ.showLog(TAG
                        + "visitSource ----> ",
                true);
        System.out.printf("    source=%s%n", source);
        System.out.printf("    debug=%s%n", debug);
        System.out.println();
    }

    @Override
    public void visitOuterClass(String owner, String name, String desc) {
        //super.visitOuterClass(owner, name, desc);
        LogShowJ.showLog(TAG
                        + "visitOuterClass ----> ",
                true);
        System.out.printf("    owner=%s$n", owner);
        System.out.printf("    name=%s$n", name);
        System.out.printf("    desc=%s$n", desc);
        System.out.println();
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        //super.visitAnnotation(desc, visible);
        LogShowJ.showLog(TAG
                        + "AnnotationVisitor ----> ",
                true);
        System.out.printf("    desc=%s", desc);
        System.out.printf("    visible=%b", visible);
        System.out.println();
        AnnotationVisitor annotation = cv.visitAnnotation(desc, visible);
        if (annotation != null) {
            annotation = new AnnotaCustom(annotation);
        }
        return annotation;
    }

    @Override
    public void visitAttribute(Attribute attr) {
        //super.visitAttribute(attr);
        LogShowJ.showLog(TAG
                        + "visitAttribute ----> ",
                true);
        System.out.printf("    attr=%s", attr.type);
        System.out.println();
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        //super.visitInnerClass(name, outerName, innerName, access);
        System.out.println();
        LogShowJ.showLog(TAG
                        + "visitInnerClass ----> ",
                true);
        System.out.printf("    name=%s, outerName=%s, innerName=%s, access=%d%n", name, outerName, innerName, access);
        System.out.println();
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        //super.visitField(access, name, desc, signature, value);
        LogShowJ.showLog(TAG
                        + "FieldVisitor ----> ",
                true);
        System.out.printf("    access=%d, name=%s, desc=%s, signature=%s%n", access, name, desc, signature);
        if (value == null) {
            System.out.println("    value is null");
        } else {
            System.out.printf("    value=%s", value.getClass());
        }
        FieldVisitor fieldVisitor = cv.visitField(access, name, desc, signature, value);
        if (fieldVisitor != null) {
            fieldVisitor = new FieldCustom(fieldVisitor);
        }
        return fieldVisitor;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        //super.visitMethod(access, name, desc, signature, exceptions);
        System.out.println();
        LogShowJ.showLog(TAG
                        + "MethodVisitor ----> ",
                true);
        System.out.printf("    access=%d, name=%s, desc=%s, signature=%s", access, name, desc, signature);
        if (exceptions == null) {
            System.out.println();
            System.out.print("    exception's length=0");
        } else {
            System.out.println();
            for (int i = 0; i < exceptions.length; i++) {
                System.out.printf("    exception[%d]=%s%n", i, exceptions[i]);
            }
        }
        MethodVisitor method = cv.visitMethod(access, name, desc, signature, exceptions);
        if (method != null) {
            method = new MethodCustom(method);
        }
        return method;
    }

    @Override
    public void visitEnd() {
        //super.visitEnd();
        System.out.println();
        System.out.println();
        LogShowJ.showLog(TAG
                        + "visitEnd ----> ",
                true);
    }
}
