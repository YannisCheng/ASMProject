package com.cwj.asm.class_loader;


import com.cwj.asm.class_type.MainClassOpCode;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.ACC_ABSTRACT;

public class MyClassLoader extends ClassLoader {

    /**
     * 调用 findClass()注意事项：name值只能为 单词字符串，不能是任何形式的路径名称
     */
    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = new byte[0];
        if (name.equals("ExampleInterface")) {
            try {
                bytes = generatingClass();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 通过ClassWriter生成的字节码文件已经是byte[]，已经存在于内存中，类加载器直接从内存中加载byte[]即可。
        return defineClass(name, bytes, 0, bytes.length);
    }

    /**
     * 生成类方法
     *
     * @return byte[]
     */
    byte[] generatingClass() throws IOException {
        ClassWriter cw = new ClassWriter(0);
        // 注意此处的 name值，应与 lassLoader.loadClass("ExampleInterface") 中的 name值 完全相同。
        // 如 "com.cwj.asm.out_file.ExampleInterface"。
        cw.visit(Opcodes.V1_8, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "ExampleInterface", null, "java/lang/Object", /*new String[]{"com/cwj/asm/out_file/Mesurable"}*/null);
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, -1).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, 0).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, 1).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        byte[] bytes = cw.toByteArray();
        // --- 本段代码的作用：只是为了查看本地实际生成的文件，对ClassLoader加载字节码文件没有任何影响 --- start
        File file = new File("/Users/yannischeng/JetBrain_Projects/Projects_IDEA_Java/AsmProject/src/main/java/com/cwj/asm/out_file/ExampleInterface.class");
        if (file.exists()) {
            if (file.delete()) {
                boolean newFile = file.createNewFile();
            }
        } else {
            boolean newFile = file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
        // --- 本段代码的作用：只是为了本地实际查看生成的文件，对ClassLoader加载字节码文件没有任何影响 --- end
        return bytes;
    }
}
