package com.cwj.asm.core_api.method_type.cost_simple;

public class TargetStaticClass {

    private static String c223Name3434;
    private static TargetStaticClass staticClass;

    public static TargetStaticClass getInstance(){
        c223Name3434 = "com/cwj/asm/core_api/method_type/cost_simple/TargetTest&targetMethod2";
        ComputeTargetCost.startTime(c223Name3434);
        if (staticClass == null) {
            staticClass = new TargetStaticClass();
        }
        ComputeTargetCost.stopTime(c223Name3434);

        return staticClass;
    }

    public void getName(){
        c223Name3434 = "com/cwj/asm/core_api/method_type/cost_simple/TargetTest&targetMethod2";
        ComputeTargetCost.startTime(c223Name3434);
        System.out.println("name");
        ComputeTargetCost.stopTime(c223Name3434);
    }
}
