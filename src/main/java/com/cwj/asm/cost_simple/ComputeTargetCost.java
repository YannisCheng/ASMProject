package com.cwj.asm.cost_simple;


/**
 * ComputeTargetCost  提供计算方法耗时开始、结束方法的类
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/5 14:17
*/
public class ComputeTargetCost {

    private static final ComputeCostRealValue costCompute = new ComputeCostRealValue();

    public static void startTime(String name){
        costCompute.startValue(name);
    }
    public static void stopTime(String name){
        costCompute.endValue(name);
    }
}

