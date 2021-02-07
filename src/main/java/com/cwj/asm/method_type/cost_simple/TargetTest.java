package com.cwj.asm.method_type.cost_simple;

/**
 * TargetTest  要计算耗时的目标类
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/5 14:16
*/
public class TargetTest {

    public void targetMethod(String name) throws InterruptedException {
        // 注意：在进行测试时，不能直接使用"ComputeTargetCost"。
        // 因为这样会导致找不到ComputeTargetCost类。
        // 应该以"全限定名称"的方式进行方法的调用。
        //com.cwj.asm.method_type.cost_simple.ComputeTargetCost.startTime(name);
        Thread.sleep(200);
        //com.cwj.asm.method_type.cost_simple.ComputeTargetCost.stopTime(name);
    }
}
/*
 * 经过ASM处理后的目标类实现计划为：
 * public class TargetTest {
 *     public TargetTest() {
 *     }
 *
 *     public void targetMethod(String name) throws InterruptedException {
 *         ComputeTargetCost.startTime(name);
 *         Thread.sleep(200L);
 *         ComputeTargetCost.stopTime(name);
 *     }
 * }
 */
