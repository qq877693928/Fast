package com.lizhenhua.fast.runtime;

import java.util.List;

public class FastTraceLog {
    public static AbstractLogAdapter logService;

    static {
        logService = new DefaultLogAdapter();
    }

    public static void setLogService(AbstractLogAdapter logService) {
        FastTraceLog.logService = logService;
    }

    public static void enterMethod(String className, String methodName, String parameterTypes, List<Object> parameterValues) {
        synchronized (FastTraceLog.class) {
            logService.enterMethod(className, methodName, parameterTypes, parameterValues);
        }
    }

    public static void exitMethod(String className, String methodName, boolean result, long lengthMillis, boolean hasReturnType) {
        exitMethod(className, methodName, Boolean.valueOf(result), lengthMillis, hasReturnType);
    }

    public static void exitMethod(String className, String methodName, int result, long lengthMillis, boolean hasReturnType) {
        exitMethod(className, methodName, Integer.valueOf(result), lengthMillis, hasReturnType);
    }

    public static void exitMethod(String className, String methodName, byte result, long lengthMillis, boolean hasReturnType) {
        exitMethod(className, methodName, Byte.valueOf(result), lengthMillis, hasReturnType);
    }

    public static void exitMethod(String className, String methodName, char result, long lengthMillis, boolean hasReturnType) {
        exitMethod(className, methodName, Character.valueOf(result), lengthMillis, hasReturnType);
    }

    public static void exitMethod(String className, String methodName, short result, long lengthMillis, boolean hasReturnType) {
        exitMethod(className, methodName, Short.valueOf(result), lengthMillis, hasReturnType);
    }

    public static void exitMethod(String className, String methodName, long result, long lengthMillis, boolean hasReturnType) {
        exitMethod(className, methodName, Long.valueOf(result), lengthMillis, hasReturnType);
    }

    public static void exitMethod(String className, String methodName, float result, long lengthMillis, boolean hasReturnType) {
        exitMethod(className, methodName, Float.valueOf(result), lengthMillis, hasReturnType);
    }

    public static void exitMethod(String className, String methodName, double result, long lengthMillis, boolean hasReturnType) {
        exitMethod(className, methodName, Double.valueOf(result), lengthMillis, hasReturnType);
    }

    public static void exitMethod(String className, String methodName, String result, long lengthMillis, boolean hasReturnType) {
        exitMethod(className, methodName, (Object) result, lengthMillis, hasReturnType);
    }

    public static void exitMethod(String className, String methodName, Object result, long lengthMillis, boolean hasReturnType) {
        synchronized (FastTraceLog.class) {
            logService.exitMethod(className, methodName, lengthMillis);
        }
    }
}
