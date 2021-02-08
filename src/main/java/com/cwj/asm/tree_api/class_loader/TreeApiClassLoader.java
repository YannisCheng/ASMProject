package com.cwj.asm.tree_api.class_loader;

import com.cwj.asm.tree_api.class_type.TreeClassGenerate;

import java.io.IOException;

public class TreeApiClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = new byte[0];
        if (name.equals("ComparableTreeApi")) {
            try {
                bytes = TreeClassGenerate.treeClassGenerate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defineClass(name, bytes, 0, bytes.length);
    }
}
