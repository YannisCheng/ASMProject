package com.cwj.asm.cost_simple;

/**
 * ComputeCostRealValue  具体记录方法开始、结束时间点的类
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/5 14:18
*/
public class ComputeCostRealValue {

    private long startTime = 0L;

    public void startValue(String name) {
        startTime = System.currentTimeMillis();
        System.out.println("statTime is :" + startTime);
    }

    public void endValue(String name) {
        long endTime = System.currentTimeMillis();
        System.out.println("endTime is :" + endTime);
        System.out.printf("method %s cost time is : %d ms%n", name, (endTime - startTime));
    }
}
