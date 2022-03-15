package com.lizhenhua.fast.demo;

import com.lizhenhua.fast.annotation.FastLog;

@FastLog
public class Test {
    private int x;
    public Test() {
        x = 0;
    }

    public Test(int value) {
        x = value;
    }

    public void testMethod(int value) {

    }

    public String testReturnString(int value) {
        return "value = " + value;
    }

    public long testMethod(long value) {
        return value + 1;
    }

    public static void testStatic() {
        System.out.println(1);
    }

    public static void testStatic(int value) {
        System.out.println(value);
    }

    public static void testStatic(int value, int value2) {

    }

    public static int testStatic(int value, int value2, int value3) {
        return (int)(value + value2 + value3);
    }

}
