package com.lizhenhua.fast.demo;

import com.lizhenhua.fast.annotation.FastLog;
import com.lizhenhua.fast.runtime.FastTraceLog;

import java.util.Arrays;

public class ReturnTest {
//    public ReturnTest() {
//        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[]", null);
//        long var4 = System.currentTimeMillis();
//        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) this, System.currentTimeMillis() - var4, false);
//    }
//
//    public ReturnTest(long value) {
//        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[java.lang.Long]", Arrays.asList(value));
//        long var4 = System.currentTimeMillis();
//        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) this, System.currentTimeMillis() - var4, false);
//    }
//
//    public ReturnTest(long value, long value2) {
//        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[java.lang.Long, java.lang.Long]", Arrays.asList(value, value2));
//        long var4 = System.currentTimeMillis();
//        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) this, System.currentTimeMillis() - var4, false);
//    }
//
//    public ReturnTest(long value, long value2, long value3) {
//        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", "[java.lang.Long, java.lang.Long, java.lang.Long]", Arrays.asList(value, value2, value3));
//        long var4 = System.currentTimeMillis();
//        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "LogTest", (Object) this, System.currentTimeMillis() - var4, false);
//    }

    public long logValue2() {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logValue2", "[]", null);
        long var4 = System.currentTimeMillis();
        int result = 1;
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logValue2", (Object) result, true);
        return result;
    }

    public long logValue(long value) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logValue", "[java.lang.Long]", Arrays.asList(value));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        long result = value + 1;
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logValue", (Object) result, true);
        return result;
    }

    public void logLong(long value, long value2) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logLong", "[java.lang.Long, java.lang.Long]", Arrays.asList(value, value2));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logLong", (Object) null, false);
    }

    public long logLong2(long value, long value2, long value3) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logLong2", "[java.lang.Long, java.lang.Long, java.lang.Long]", Arrays.asList(value, value2, value3));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        long result = value + value2 + value3;
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logLong2", (Object) result, true);
        return result;
    }

    public long logLong(long value, long value2, long value3) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logLong", "[java.lang.Long, java.lang.Long, java.lang.Long]", Arrays.asList(value, value2, value3));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        long result = value + value2 + value3;
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logLong", (Object) result, true);
        return result;
    }

    public static void logValue() {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logValue", "[java.lang.Integer]", null);
        long var4 = System.currentTimeMillis();
        System.out.println(1);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logValue", (Object) null, false);
    }

    public static void logValue(int value) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logValue", "[java.lang.Integer]", Arrays.asList(value));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logValue", (Object) null, false);
    }

    public static void logValue2(int value, int value2) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logValue2", "[java.lang.Integer, java.lang.Integer]", Arrays.asList(value, value2));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logValue2", (Object) null, false);
    }

    public static long logValue2(int value, int value2, int value3) {
        FastTraceLog.enterMethod("com.lizhenhua.fast.demo.LogTest", "logValue2", "[java.lang.Integer, java.lang.Integer, java.lang.Integer]", Arrays.asList(value, value2, value3));
        long var4 = System.currentTimeMillis();
        System.out.println(value);
        long result = value + value2 + value3;
        FastTraceLog.exitMethod("com.lizhenhua.fast.demo.LogTest", "logInt", (Object) result, true);
        return result;
    }
}
