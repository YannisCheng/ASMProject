package com.cwj.asm.core_api.class_type;

import com.cwj.asm.core_api.method_type.cost_simple.ClassComputeCostAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.ASM6;

/**
 * MainClassOpCode ASM Core API Class 基本操作处理
 */
public class MainClassOpCode {

    static final String packageName = "com/cwj/asm/out_file/";


    /**
     * 通过ASM中的 ClassVisitor 与 MethodVisitor 修改类中方法的实现。
     */
    public static byte[] dealNopInsn() throws IOException {
        PrintWriter printWriter = new PrintWriter(System.out, true);
        ClassWriter classWriter = new ClassWriter(0);
        // 配合PrintWriter，在控制台追踪输出ASM代码。
        //TraceClassVisitor traceClassVisitor = new TraceClassVisitor(classWriter, printWriter);
        // 直接复制
        //ClassVisitor classVisitor = new ClassVisitor(ASM6,classWriter) {};
        // 最好使用CheckClassAdapter类，以对ASM的筛选过程进行check，便于异常问题的定位。
        CheckClassAdapter checkClassAdapter = new CheckClassAdapter(classWriter);
        ClassComputeCostAdapter adapter = new ClassComputeCostAdapter(checkClassAdapter);
        // 该处的name值必须为：全限定名称，即：包名+类名
        ClassReader reader = new ClassReader("com.cwj.asm.core_api.method_type.cost_simple.TargetTest");
        reader.accept(adapter, 0);
        byte[] bytes = classWriter.toByteArray();
        // 该处的 writeToFile() 对 执行自定义ClassLoader没有任何影响，只是为了本地查看实际生成代码使用。
        writeToFile("NewTargetTest.class", bytes);
        return bytes;
    }

    /**
     * 向原字节码文件中添加新field
     *
     * @throws IOException .
     */
    public static void addField() throws IOException {
        ClassWriter classWriter = new ClassWriter(0);
        AddFieldAdapter adapter = new AddFieldAdapter(classWriter, ACC_PUBLIC + ACC_FINAL, "yannisName", "Ljava/lang/String;");
        ClassReader classReader = new ClassReader("java.io.FileOutputStream");
        classReader.accept(adapter, 0);
        byte[] bytes = classWriter.toByteArray();
        writeToFile("FileOutputStreamAddField.class", bytes);
    }


    /**
     * 移除方法体
     *
     * @throws IOException .
     */
    public static void removeMethodAndFiled() throws IOException {
        ClassWriter classWriter = new ClassWriter(0);
        ClassPrinter classPrinter = new ClassPrinter(classWriter);
        ClassReader classReader = new ClassReader("java.io.FileOutputStream");
        classReader.accept(classPrinter, 0);
        byte[] bytes = classWriter.toByteArray();
        writeToFile("FileOutputStream3.class", bytes);
    }

    /**
     * 分析已有类
     */
    public static void parsingClass() throws IOException {
        ClassPrinter classPrinter = new ClassPrinter();
        ClassReader classReader = new ClassReader("java.io.FileOutputStream");
        classReader.accept(classPrinter, 0);
    }

    /**
     * 生成class文件：
     * 例如，目标文件为：
     * package com.cwj.asm;
     * public interface Comparable extends Mesurable{
     * int LESS = -1;
     * int EQUAL = 0;
     * int GREATER = 1;
     * int compareTo(Object o);
     * }
     */
    public static void generatingClass() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_8, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, packageName + "Comparable", null, "java/lang/Object", /*new String[]{packageName + "Mesurable"}*/null);
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, -1).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, 0).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, 1).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        byte[] bytes = cw.toByteArray();
        writeToFile("Comparable.class", bytes);
    }

    /**
     * org.objectweb.asm.util中使用到的工具类：
     * 1. CheckClassAdapter
     * 2. TraceClassVisitor
     * <p>
     * 编码正常时，控制台输出：
     * // class version 52.0 (52)
     * // access flags 0x601
     * public abstract interface com/cwj/asm/Comparable implements com/cwj/asm/Mesurable  {
     * <p>
     * // access flags 0x19
     * public final static I LESS = -1
     * <p>
     * // access flags 0x19
     * public final static I EQUAL = 0
     * <p>
     * // access flags 0x19
     * public final static I GREATER = 1
     * <p>
     * // access flags 0x401
     * public abstract compareTo(Ljava/lang/Object;)I
     * }
     * <p>
     * 编码异常时，控制台输出：
     * error: Invalid descriptor: II
     */
    public static void generatingClassWithTraceWithCheck() {
        PrintWriter printWriter = new PrintWriter(System.out, true);
        ClassWriter cw = new ClassWriter(0);
        // 注意 checkClassAdapter 的使用顺序，不同的使用顺序，导致的结果不同。
        // 1. 先执行trace，后执行check
        /*CheckClassAdapter checkClassAdapter = new CheckClassAdapter(cw);
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(checkClassAdapter, printWriter);
        traceClassVisitor.visit(V1_8, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, packageName + "Comparable", null, "java/lang/Object", new String[]{packageName + "Mesurable"});
        traceClassVisitor.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "II", null, -1).visitEnd();
        traceClassVisitor.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, 0).visitEnd();
        traceClassVisitor.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, 1).visitEnd();
        traceClassVisitor.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
        traceClassVisitor.visitEnd();*/

        // 2. 先执行check，后执行trace
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(cw, printWriter);
        try {
            CheckClassAdapter checkClassAdapter = new CheckClassAdapter(traceClassVisitor);
            checkClassAdapter.visit(V1_8, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, packageName + "Comparable", null, "java/lang/Object", new String[]{packageName + "Mesurable"});
            // 可通过将此处的 "I" 修改为 "II"，即可触发异常。
            checkClassAdapter.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, -1).visitEnd();
            checkClassAdapter.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, 0).visitEnd();
            checkClassAdapter.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, 1).visitEnd();
            checkClassAdapter.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
            checkClassAdapter.visitEnd();
        } catch (IllegalArgumentException exception) {
            System.out.println("error: " + exception.getMessage());
        }

        byte[] bytes = cw.toByteArray();
        writeToFile("Comparable.class", bytes);
    }

    /**
     * 转换类-1：复制类(转换链：读取器 -> 适配器 -> 编写器)
     *
     * @throws IOException .
     */
    public static void copyClass() throws IOException {
        long startTime = System.currentTimeMillis();
        ClassWriter classWriter = new ClassWriter(0);
        ClassVisitor classVisitor = new ClassVisitor(ASM6, classWriter) {
        };
        ClassReader classReader = new ClassReader("java.io.FileOutputStream");
        classReader.accept(classVisitor, 0);
        byte[] bytes = classWriter.toByteArray();
        long endTime = System.currentTimeMillis();
        System.out.println("copy cost time is : " + (endTime - startTime) + "ms");
        writeToFile("FileOutputStream2.class", bytes);
    }

    /**
     * 转换类-2：变更顺序
     *
     * @throws IOException .
     */
    public static void copyClass2() throws IOException {
        long startTime = System.currentTimeMillis();
        ClassReader classReader = new ClassReader("java.io.FileOutputStream");
        ClassWriter classWriter = new ClassWriter(classReader, 0);
        ClassVisitor classVisitor = new ClassVisitor(ASM6, classWriter) {
        };
        classReader.accept(classVisitor, 0);
        byte[] bytes = classWriter.toByteArray();
        long endTime = System.currentTimeMillis();
        System.out.println("copy cost time is : " + (endTime - startTime) + "ms");
        writeToFile("FileOutputStream2.class", bytes);
    }

    /**
     * byte 写入文件
     *
     * @param name  文件名称
     * @param bytes 数据
     */
    public static void writeToFile(String name, byte[] bytes) {
        try {
            File file = new File("/Users/yannischeng/JetBrain_Projects/Projects_IDEA_Java/AsmProject/src/main/java/com/cwj/asm/out_file/" + name);
            FileOutputStream outputStream = null;
            if (file.exists()) {
                if (file.delete()) {
                    if (file.createNewFile()) {
                        outputStream = new FileOutputStream(file);
                        outputStream.write(bytes);
                    }
                }
            } else {
                outputStream = new FileOutputStream(file);
                outputStream.write(bytes);
            }
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
