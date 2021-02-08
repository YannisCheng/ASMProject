package com.cwj.asm.tree_api.class_type;

import org.objectweb.asm.tree.ClassNode;

/**
 * RemoveMethodAdapter  移除类中的方法
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/8 09:57
*/
public class RemoveMethodAdapter {

    private final String methodName;
    private final String methodDesc;

    public RemoveMethodAdapter(String mMethodName, String mMethodDesc) {
        this.methodName = mMethodName;
        this.methodDesc = mMethodDesc;
    }

    public void transform(ClassNode classNode) {
        /*
         * Iterator<MethodNode> iterator = classNode.methods.iterator();
         * while (iterator.hasNext()) {
         *     MethodNode next = iterator.next();
         *     if (methodName.equals(next.name) && methodDesc.equals(next.desc)) {
         *         iterator.remove();
         *     }
         * }
         */
        classNode.methods.removeIf(next -> methodName.equals(next.name) && methodDesc.equals(next.desc));
    }
}
