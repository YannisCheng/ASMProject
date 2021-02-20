package com.cwj.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author wenjia.Cheng  cwj1714@163.com
 * @date 2021/2/19
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TargetClass targetClass = new TargetClass();
        targetClass.showCostTime();
    }
}
