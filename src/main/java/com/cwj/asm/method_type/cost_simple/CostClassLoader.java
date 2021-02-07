package com.cwj.asm.method_type.cost_simple;


import com.cwj.asm.class_type.MainClassOpCode;

import java.io.IOException;

/**
 * CostClassLoader  自定义类加载器
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/5 14:19
*/
public class CostClassLoader extends ClassLoader {

    /**
     * 注意：该处的name值只能为 单词字符串，不能是任何形式的路径名称
     */
    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = new byte[0];
        if (name.equals("TargetTest")) {
            try {
                // 通过ClassWriter生成的字节码文件已经是byte[]，已经存在于内存中，类加载器直接从内存中加载byte[]即可。
                bytes = MainClassOpCode.dealNopInsn();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 该处的name值必须为：包名+类名
        return defineClass("com.cwj.asm.method_type.cost_simple.TargetTest", bytes, 0, bytes.length);
    }
}
