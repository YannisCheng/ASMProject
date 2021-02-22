package com.cwj.asm.core_api.method_type.cost_simple;

/**
 * ComputeCostRealValue  具体记录方法开始、结束时间点的类
 *
 * @author wenjia.Cheng  cwj1714@163.com
 * @date 2021/2/5 14:18
 */
public class ComputeCostRealValue {

    private long startTime = 0L;

    public void startValue(String methodN) {
        startTime = System.currentTimeMillis();
        System.out.printf("methodN is : %s%n statTime is : %d ms %n", methodN, startTime);
    }

    public void endValue(String className) {
        long endTime = System.currentTimeMillis();
        System.out.println(" endTime is :" + endTime);
        System.out.printf("className is %s, cost time is : %d ms%n", className, (endTime - startTime));
    }
}
