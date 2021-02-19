package com.cwj.asm.tree_api.method_type.cost_simple;

import org.objectweb.asm.tree.*;

import java.util.ListIterator;

import static org.objectweb.asm.Opcodes.*;

public class AddCostTimeMethod {

    public void transform(ClassNode classNode) {
        for (MethodNode method : classNode.methods) {

            if (method.name.equals("<init>") || method.name.equals("<clinit>")) {
                continue;
            }

            InsnList insnList = method.instructions;
            if (insnList.size() == 0) {
                continue;
            }

            ListIterator<AbstractInsnNode> iterator = insnList.iterator();
            while (iterator.hasNext()) {
                AbstractInsnNode next = iterator.next();
                int opcode = next.getOpcode();
                // 在该方法的退出指令之前插入结束计时指令
                if (opcode >= IRETURN && opcode <= RETURN || opcode == ATHROW) {
                    InsnList newInsn = new InsnList();
                    // 具体方法插入指令
                    newInsn.add(new VarInsnNode(ALOAD, 1));
                    newInsn.add(new MethodInsnNode(INVOKESTATIC, "com/cwj/asm/tree_api/method_type/cost_simple", "endTime", "(Ljava/lang/String;)V", false));
                    insnList.insert(next.getPrevious(), newInsn);
                }
            }

            // 插入开始计时指令
            InsnList newInsn = new InsnList();
            // 具体方法插入指令
            newInsn.add(new VarInsnNode(ALOAD, 1));
            newInsn.add(new MethodInsnNode(INVOKESTATIC, "com/cwj/asm/tree_api/method_type/cost_simple", "startTime", "(Ljava/lang/String;)V", false));
            // 将新添加的指令插入原方法指令集的链表头部
            insnList.insert(newInsn);
        }

    }
}
