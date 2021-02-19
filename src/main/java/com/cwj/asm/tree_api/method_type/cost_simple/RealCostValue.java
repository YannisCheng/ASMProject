package com.cwj.asm.tree_api.method_type.cost_simple;

public class RealCostValue {

    private long startTime;

    public void setStartTime(String name) {
        startTime = System.currentTimeMillis();
        System.out.println("stat time is : " + startTime);
    }

    public void setEndTime(String name) {
        long endTime = System.currentTimeMillis();
        System.out.println("end time is : " + endTime);
        System.out.println("cost time is : " + (endTime - startTime));
    }
}
