package com.cwj.asm.tree_api.class_type;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

public class TreeClassGenerate {

    public static byte[] treeClassGenerate() throws IOException {
        ClassWriter classWriter = new ClassWriter(0);
        ClassNode classNode = new ClassNode();
        classNode.version = V1_8;
        classNode.access = ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE;
        // 使用自定义的ClassLoader加载该类时，不用使用包名，导致找不到具体路径下的文件；
        // 直接使用文件名称方便进行测试。
        classNode.name = "ComparableTreeApi";
        classNode.superName = "java/lang/Object";
        classNode.fields.add(new FieldNode(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, new Integer(-1)));
        classNode.fields.add(new FieldNode(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, new Integer(0)));
        classNode.fields.add(new FieldNode(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, new Integer(1)));
        classNode.methods.add(new MethodNode(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null));
        classNode.accept(classWriter);
        byte[] bytes = classWriter.toByteArray();
        // --- 本段代码的作用：只是为了查看本地实际生成的文件，对ClassLoader加载字节码文件没有任何影响 --- start
        File file = new File("/Users/yannischeng/JetBrain_Projects/Projects_IDEA_Java/AsmProject/src/main/java/com/cwj/asm/out_file/ComparableTreeApi.class");
        if (file.exists()) {
            if (file.delete()) {
                boolean newFile = file.createNewFile();
            }
        } else {
            boolean newFile = file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.flush();
        fos.close();
        // --- 本段代码的作用：只是为了本地实际查看生成的文件，对ClassLoader加载字节码文件没有任何影响 --- end
        return bytes;
    }
}
