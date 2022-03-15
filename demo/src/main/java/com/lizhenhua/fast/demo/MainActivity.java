package com.lizhenhua.fast.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;

import com.lizhenhua.fast.annotation.FastLog;
import com.lizhenhua.fast.annotation.LogPolicy;
//import com.lizhenhua.fast.lib.AndroidLogTest;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    @SuppressLint("WrongThread")
    @FastLog
    @Override
    @AnyThread
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testIntArray(1, 2, 3, 4);

        new Test(2).testReturnString(5);
        new Test(2).testMethod(5);

        Test.testStatic(1, 2, 3);

        new KotlinTest(1, 2, 3);

        new KotlinTest("1", "2", "3").logLong(1L, 2L);

        KotlinTest.Companion.logValue2(1L, 2L, 3L);

//        new AndroidLogTest().logLong(1L, 2L, 3L);

    }

    @FastLog(LogPolicy.ONLY_METHOD)
    public void onTestInt(View view) {
        testInt(1);
    }

    @FastLog
    public void onTestBoolean(View view) {
        testBoolean(true);
    }

    @FastLog
    @AnyThread
    public void onTestIntArray(View view) {
        testIntArray(Arrays.asList(1, 2));
    }

    @FastLog
    private void testIntArray(List<Integer> arrayOf) {
        System.out.println(arrayOf);
    }

    @FastLog
    private void testInt(int a) {
        System.out.println(a);
    }

    @FastLog
    private static void testBoolean(boolean b) {
        System.out.println(b);
    }

    @FastLog
    public static synchronized int testIntArray(int value, int value2, int value3, int value4) {
        System.out.println(value);
        return value + value2 + value3 + value4;
    }
}
