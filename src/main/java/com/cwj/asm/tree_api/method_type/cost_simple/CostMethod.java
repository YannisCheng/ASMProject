package com.cwj.asm.tree_api.method_type.cost_simple;

public class CostMethod {

    private static final RealCostValue realCostValue = new RealCostValue();

    public static void startTime(String name){
        realCostValue.setStartTime(name);
    }

    public static void endTime(String name){
        realCostValue.setEndTime(name);
    }
}
