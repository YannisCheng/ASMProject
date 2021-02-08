package com.cwj.asm.tree_api;

import com.cwj.asm.tree_api.class_type.AddFieldAdapter;
import com.cwj.asm.tree_api.class_type.RemoveMethodAdapter;
import com.cwj.asm.utils.OutPutFile;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

public class TreeApiMainTest {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        // 加载ASM Tree API 生成的class文件
        /*TreeApiClassLoader loader = new TreeApiClassLoader();
        loader.loadClass("ComparableTreeApi");*/

        //treeApiRemoveMethod();
        treeApiAddField();
    }

    /**
     * 测试使用 Tree API 进行字段添加
     */
    private static void treeApiAddField() throws IOException {
        ClassWriter classWriter = new ClassWriter(0);
        ClassReader classReader = new ClassReader("com.cwj.asm.utils.TaskTestClass");
        ClassNode classNode = new ClassNode();
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
