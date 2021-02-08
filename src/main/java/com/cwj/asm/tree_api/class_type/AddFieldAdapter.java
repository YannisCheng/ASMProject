package com.cwj.asm.tree_api.class_type;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class AddFieldAdapter {

    private final String name;
    private final String desc;
    private final int access;


    public AddFieldAdapter(String name, String desc, int access) {
        this.name = name;
        this.desc = desc;
        this.access = access;
    }

    public void transform(ClassNode classNode) {
        boolean isPresent = false;
        for (FieldNode fieldNode : classNode.fields) {
            if (name.equals(fieldNode.name)) {
                isPresent = true;
                break;
            }
        }

        if (!isPresent) {
            classNode.fields.add(new FieldNode(access, name, desc, null, "Petter"));
        }
    }
}
