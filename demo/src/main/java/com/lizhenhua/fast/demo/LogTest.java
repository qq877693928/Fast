package com.lizhenhua.fast.demo;

import com.lizhenhua.fast.annotation.FastLog;
import com.lizhenhua.fast.runtime.FastTraceLog;

import java.util.ArrayList;
import java.util.Arrays;

public class LogTest {
    public LogTest() {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[java.lang.Long, java.lang.Long]", null);
        long var4 = System.currentTimeMillis();
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) null, System.currentTimeMillis() - var4, false);
    }

    public LogTest(long value) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[java.lang.Long, java.lang.Long]", Arrays.asList(value));
        long var4 = System.currentTimeMillis();
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) null, System.currentTimeMillis() - var4, false);
    }

    public LogTest(int value) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[java.lang.Integer]", Arrays.asList(value));
        long var4 = System.currentTimeMillis();
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) null, System.currentTimeMillis() - var4, false);
    }

    public LogTest(long value, long value2) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[java.lang.Long, java.lang.Long]", Arrays.asList(value, value2));
        long var4 = System.currentTimeMillis();
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) null, System.currentTimeMillis() - var4, false);
    }

    public LogTest(long value, long value2, long value3) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[java.lang.Long, java.lang.Long, java.lang.Long]", Arrays.asList(value, value2, value3));
        long var4 = System.currentTimeMillis();
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) null, System.currentTimeMillis() - var4, false);
    }

    public void logLong(long value, long value2, long value3) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logInt", "[java.lang.Long, java.lang.Long, java.lang.Long]", Arrays.asList(value, value2, value3));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logInt", (Object) null, System.currentTimeMillis() - var4, false);
    }

    public void logValue(long value) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logInt", "[java.lang.Long]", Arrays.asList(value));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logInt", (Object) null, System.currentTimeMillis() - var4, false);
    }

    public void logLong(long value, long value2) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logInt", "[java.lang.Long, java.lang.Long]", Arrays.asList(value, value2));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logInt", (Object) null, System.currentTimeMillis() - var4, false);
    }

    public long logReturn(long value) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logInt", "[java.lang.Long]", Arrays.asList(value));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        long result = value + 1L;
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logInt", (Object) result, System.currentTimeMillis() - var4, true);
        return result;
    }

    public static void logValue() {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logInt", "[java.lang.Integer]", null);
        long var4 = System.currentTimeMillis();
        System.out.println(1);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logInt", (Object) null, System.currentTimeMillis() - var4, false);
    }

    public static void logValue(int value) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logInt", "[java.lang.Integer]", Arrays.asList(value));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logInt", (Object) null, System.currentTimeMillis() - var4, false);
    }

    public static void logValue2(int value, int value2) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logInt", "[java.lang.Integer, java.lang.Integer]", Arrays.asList(value, value2));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logInt", (Object) null, System.currentTimeMillis() - var4, false);
    }

    public static void logValue2(int value, int value2, int value3) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logInt", "[java.lang.Integer, java.lang.Integer, java.lang.Integer]", Arrays.asList(value, value2, value3));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logInt", (Object) null, System.currentTimeMillis() - var4, false);
    }

    public static void logValue2(long value, int value2, long value3) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logInt", "[java.lang.Integer, java.lang.Integer, java.lang.Integer]", Arrays.asList(value, value2, value3));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logInt", (Object) null, System.currentTimeMillis() - var4, false);
    }
}
