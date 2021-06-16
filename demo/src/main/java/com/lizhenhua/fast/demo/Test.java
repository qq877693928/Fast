package com.lizhenhua.fast.demo;

import com.lizhenhua.fast.annotation.FastLog;

public class Test {
    private int x;
    @FastLog
    public Test() {
        x = 0;
    }

    @FastLog
    public Test(int value) {
        x = value;
    }

    @FastLog
    public void testMethod(int value) {

    }

    @FastLog
    public String testReturnString(int value) {
        return "value = " + value;
    }

    @FastLog
    public long testMethod(long value) {
        return value + 1;
    }

    @FastLog
    public static void testStatic() {
        System.out.println(1);
    }

    @FastLog
    public static void testStatic(int value) {
        System.out.println(value);
    }

    @FastLog
    public static void testStatic(int value, int value2) {

    }

    @FastLog
    public int testStatic(int value, int value2, int value3) {
        return (int)(value + value2 + value3);
    }

}
