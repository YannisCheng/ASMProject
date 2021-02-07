package com.cwj.asm;

import com.cwj.asm.class_loader.MyClassLoader;
import com.cwj.asm.class_type.MainClassOpCode;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, NoSuchFieldException {
        // 类操作
        MainClassOpCode.parsingClass();
//        MainClassOpCode.generatingClass();
//        MainClassOpCode.copyClass();
//        MainClassOpCode.copyClass2();
//        MainClassOpCode.removeMethodAndFiled();
//        MainClassOpCode.addField();
//        MainClassOpCode.generatingClassWithTraceWithCheck();

        // 获取 字节码文件，与之后要执行的ClassLoader分开执行。
        //byte[] bytes = MainClassOpCode.dealNopInsn();
        // 加载内存中的字节码文件
        //MyClassLoader myClassLoader = new MyClassLoader();

        // 纯 ClassWriter 生成的字节码执行
        /*Class aClass = myClassLoader.loadClass("ExampleInterface");
        System.out.println("loadclass name is " + aClass.getName());
        Field field = aClass.getField("LESS");
        System.out.println("field is : " +field.getInt(null));*/


    }


}
