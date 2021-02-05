package com.cwj.asm.class_type;

import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 * ASM Core API Class 整体分析
 */
public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() {
        super(ASM6);
    }

    public ClassPrinter(ClassVisitor cv) {
        super(ASM6, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        System.out.println("visit ---->");

        System.out.printf("    version=%d, access=%d, name=%s, signature=%s, superName=%s interfaces size=%d%n", version, access, name, signature, superName, interfaces.length);
        for (int i = 0; i < interfaces.length; i++) {
            System.out.printf("    interfaces[%d]=%s%n", i, interfaces[i]);
        }
        System.out.print("");
    }

    @Override
    public void visitSource(String source, String debug) {
        super.visitSource(source, debug);
        System.out.println("visitSource ---->");
        System.out.printf("    source=%s%n", source);
        System.out.printf("    debug=%s%n", debug);
        System.out.println();
    }

    @Override
    public void visitOuterClass(String owner, String name, String desc) {
        super.visitOuterClass(owner, name, desc);
        System.out.println("visitOuterClass ---->");
        System.out.printf("    owner=%s$n", owner);
        System.out.printf("    name=%s$n", name);
        System.out.printf("    desc=%s$n", desc);
        System.out.println();
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        super.visitAnnotation(desc, visible);
        System.out.println("AnnotationVisitor ---->");
        System.out.printf("    desc=%s", desc);
        System.out.printf("    visible=%b", visible);
        System.out.println();
        return null;
    }

    @Override
    public void visitAttribute(Attribute attr) {
        super.visitAttribute(attr);
        System.out.println("visitAttribute ---->");
        System.out.printf("    attr=%s", attr.type);
        System.out.println();
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        super.visitInnerClass(name, outerName, innerName, access);
        System.out.println("visitInnerClass ---->");
        System.out.printf("    name=%s, outerName=%s, innerName=%s, access=%d%n", name, outerName, innerName, access);
        System.out.println();
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        super.visitField(access, name, desc, signature, value);
        System.out.println("FieldVisitor ---->");
        System.out.printf("    access=%d, name=%s, desc=%s, signature=%s%n", access, name, desc, signature);
        if (value == null) {
            System.out.println("    value is null");
        } else {
            System.out.printf("    value=%s", value.getClass());
        }
        return null;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        super.visitMethod(access, name, desc, signature, exceptions);
        System.out.println();
        System.out.println("MethodVisitor ---->");
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
        return null;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        System.out.println();
        System.out.println();
        System.out.println("visitEnd ---->");
    }
}
