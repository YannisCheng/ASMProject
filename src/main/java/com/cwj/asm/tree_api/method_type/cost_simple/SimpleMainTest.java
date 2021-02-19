package com.cwj.asm.tree_api.method_type.cost_simple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SimpleMainTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class<?> targetTestClass = customClassLoader.loadClass("TargetTestClass");
        Method method = targetTestClass.getDeclaredMethod("showHello", String.class);
        method.invoke(targetTestClass.newInstance(), "Cost");
    }
}
