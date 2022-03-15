package com.lizhenhua.fast.demo;

import com.lizhenhua.fast.runtime.FastTraceLog;

import java.util.Arrays;

public class LogTest {
    public LogTest() {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[java.lang.Long, java.lang.Long]", null);
        long var4 = System.currentTimeMillis();
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) null, false);
    }

    public LogTest(long value) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[java.lang.Long, java.lang.Long]", Arrays.asList(value));
        long var4 = System.currentTimeMillis();
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) null, false);
    }

    public LogTest(int value) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[java.lang.Integer]", Arrays.asList(value));
        long var4 = System.currentTimeMillis();
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) null, false);
    }

    public LogTest(long value, long value2) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[java.lang.Long, java.lang.Long]", Arrays.asList(value, value2));
        long var4 = System.currentTimeMillis();
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) null, false);
    }

    public LogTest(int value, int value2, int value3) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[java.lang.Integer, java.lang.Integer, java.lang.Integer]", Arrays.asList(value, value2, value3));
        long var4 = System.currentTimeMillis();
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) null, false);
    }

    public LogTest(long value, long value2, long value3) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[java.lang.Long, java.lang.Long, java.lang.Long]", Arrays.asList(value, value2, value3));
        long var4 = System.currentTimeMillis();
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) null, false);
    }

    public void logLong(long value, long value2, long value3) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logLong", "[java.lang.Long, java.lang.Long, java.lang.Long]", Arrays.asList(value, value2, value3));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logLong", (Object) null, false);
    }

    public void logLong(long value) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logLong", "[java.lang.Long]", Arrays.asList(value));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logLong", (Object) null, false);
    }

    public void logLong(long value, long value2) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logLong", "[java.lang.Long, java.lang.Long]", Arrays.asList(value, value2));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logLong", (Object) null, false);
    }

    public long logReturn(long value) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logReturn", "[java.lang.Long]", Arrays.asList(value));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        long result = value + 1L;
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logReturn", (Object) result, true);
        return result;
    }

    public static void logInt() {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logInt", "[java.lang.Integer]", null);
        long var4 = System.currentTimeMillis();
        System.out.println(1);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logInt", (Object) null, false);
    }

    public static void logInt(int value) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logInt", "[java.lang.Integer]", Arrays.asList(value));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logInt", (Object) null, false);
    }

    public static void logInt(int value, int value2) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logInt", "[java.lang.Integer, java.lang.Integer]", Arrays.asList(value, value2));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logInt", (Object) null, false);
    }

    public static void logInt(int value, int value2, int value3) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logInt", "[java.lang.Integer, java.lang.Integer, java.lang.Integer]", Arrays.asList(value, value2, value3));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logInt", (Object) null, false);
    }

    public static void logMultipleValue(long value, int value2, long value3) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logMultipleValue", "[java.lang.Integer, java.lang.Integer, java.lang.Integer]", Arrays.asList(value, value2, value3));
        long var4 = System.currentTimeMillis();
        System.out.println(var4);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logMultipleValue", (Object) null, false);
    }
}
