package com.cwj.asm.tree_api.method_type.cost_simple;


import com.cwj.asm.utils.OutPutFile;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;

public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //super.findClass(name);

        byte[] bytes = new byte[0];
        if (name.equals("TargetTestClass")) {
            try {
                bytes = getFileBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return defineClass("com.cwj.asm.tree_api.method_type.cost_simple.TargetTestClass", bytes, 0, bytes.length);
    }

    private byte[] getFileBytes() throws IOException {
        ClassWriter classWriter = new ClassWriter(0);
        ClassReader classReader = new ClassReader("com.cwj.asm.tree_api.method_type.cost_simple.TargetTestClass");
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode,0);
        AddCostTimeMethod addCostTimeMethod = new AddCostTimeMethod();
        addCostTimeMethod.transform(classNode);
        classNode.accept(classWriter);
        byte[] bytes = classWriter.toByteArray();
        OutPutFile.outBytes(bytes,"TargetTestClassCustom.class");
        return bytes;
    }
}