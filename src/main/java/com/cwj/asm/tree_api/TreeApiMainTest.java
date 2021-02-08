package com.cwj.asm.tree_api;

import com.cwj.asm.tree_api.class_type.AddFieldAdapter;
import com.cwj.asm.tree_api.class_type.ClassTreeCopyPatternDelegation;
import com.cwj.asm.tree_api.class_type.ClassTreeCopyPatternExtends;
import com.cwj.asm.tree_api.class_type.RemoveMethodAdapter;
import com.cwj.asm.utils.OutPutFile;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

public class TreeApiMainTest {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        // 加载ASM Tree API 生成的class文件
        /*TreeApiClassLoader loader = new TreeApiClassLoader();
        loader.loadClass("ComparableTreeApi");*/

        //treeApiRemoveMethod();
        //treeApiAddField();
        //treeApiCopy();
        treeApiCopy2();

    }

    /**
     * 同样是执行了一个 "class文件复制" 的工作，不同之处在于：将ClassWriter置于ClassNode#visitEnd()中
     *
     * @throws IOException .
     */
    private static void treeApiCopy2() throws IOException {
        PrintWriter printWriter = new PrintWriter(System.out, true);
        ClassWriter classWriter = new ClassWriter(0);
        // 类转换方式2：委托方式
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(classWriter, printWriter);
        //ClassVisitor treeCopy = new ClassTreeCopyPatternDelegation(traceClassVisitor);
        // 类转换方式1：继承方式
        ClassVisitor treeCopy = new ClassTreeCopyPatternExtends(traceClassVisitor);
        //ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader("com.cwj.asm.utils.TaskTestClass");
        classReader.accept(treeCopy, 0);
        //classReader.accept(classNode, 0);
        //classNode.accept(classWriter);
        byte[] bytes = classWriter.toByteArray();
        try {
            OutPutFile.outBytes(bytes, "TaskTestClassCopy2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制 class文件
     *
     * @throws IOException .
     */
    private static void treeApiCopy() throws IOException {
        ClassWriter classWriter = new ClassWriter(0);
        ClassReader classReader = new ClassReader("com.cwj.asm.utils.TaskTestClass");
        // 不做任何修改直接输出字节码内容
        classReader.accept(classWriter, 0);
        byte[] bytes = classWriter.toByteArray();
        try {
            OutPutFile.outBytes(bytes, "TaskTestClassCopy");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试使用 Tree API 进行字段添加
     */
    private static void treeApiAddField() throws IOException {
        ClassWriter classWriter = new ClassWriter(0);
        ClassReader classReader = new ClassReader("com.cwj.asm.utils.TaskTestClass");
        ClassNode classNode = new ClassNode();
        // 异常
        //classNode.accept(classWriter);
        classReader.accept(classNode, 0);
        AddFieldAdapter addFieldAdapter = new AddFieldAdapter("addField", "Ljava/lang/String;", ACC_PUBLIC);
        addFieldAdapter.transform(classNode);
        classNode.accept(classWriter);
        byte[] bytes = classWriter.toByteArray();
        try {
            OutPutFile.outBytes(bytes, "TaskTestClassAddField");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试使用 Tree API 进行方法移除
     *
     * @throws IOException .
     */
    private static void treeApiRemoveMethod() throws IOException {
        ClassWriter classWriter = new ClassWriter(0);
        ClassReader classReader = new ClassReader("com.cwj.asm.utils.TaskTestClass");
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode, 0);
        RemoveMethodAdapter methodAdapter = new RemoveMethodAdapter("showHello", "()V");
        methodAdapter.transform(classNode);
        classNode.accept(classWriter);
        byte[] bytes = classWriter.toByteArray();
        try {
            OutPutFile.outBytes(bytes, "TaskTestClassRemoveMethod");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
