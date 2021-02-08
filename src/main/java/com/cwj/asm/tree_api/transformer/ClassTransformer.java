package com.cwj.asm.tree_api.transformer;

import org.objectweb.asm.tree.ClassNode;

/**
 * ClassTransformer  类转换基础工具类
 *
 * @author wenjia.Cheng  cwj1714@163.com
 * @date 2021/2/8 09:10
 */
public class ClassTransformer {

    protected ClassTransformer classTransformer;

    public ClassTransformer(ClassTransformer classTransformer) {
        this.classTransformer = classTransformer;
    }

    public void transform(ClassNode classNode) {
        if (classTransformer != null) {
            classTransformer.transform(classNode);
        }
    }
}
