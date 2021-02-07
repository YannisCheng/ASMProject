package com.cwj.asm.metadata_type.annotation;

import com.cwj.asm.metadata_type.annotation.annotations.AnnotaAll;
import com.cwj.asm.metadata_type.annotation.annotations.AnnotaFruitColor;
import com.cwj.asm.metadata_type.annotation.annotations.AnnotaMethod;

/**
 * TargetAnnotaClass  注解测试的 目标示例
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/7 14:12
*/
@AnnotaAll
public class TargetAnnotaClass {

    @AnnotaAll
    public String myName = "Test Hello";

    @AnnotaFruitColor
    public int appleColor;

    @AnnotaFruitColor(fruitColor = AnnotaFruitColor.Color.RED)
    public int orangeColor;

    @AnnotaMethod
    public void sayHello() {
        System.out.println("Hello word");
    }

    @AnnotaMethod(methodN = "notice this method")
    public void printOrangeColor() {
        System.out.println("orangeColor is :" + orangeColor);
    }

    public void printAppleColor() {
        System.out.println("appleColor is :" + appleColor);
    }

    public void printBananaColor(AnnotaFruitColor.Color bananaColor){
        System.out.println("Banana color is :" + bananaColor);
    }

}
