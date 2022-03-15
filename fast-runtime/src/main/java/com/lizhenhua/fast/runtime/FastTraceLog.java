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
            if (logService == null) {
                return;
            }
            logService.enterMethod(className, methodName, parameterTypes, parameterValues);
        }
    }

    public static void exitMethod(String className, String methodName, boolean result, boolean hasReturnType) {
        exitMethod(className, methodName, Boolean.valueOf(result), hasReturnType);
    }

    public static void exitMethod(String className, String methodName, int result, boolean hasReturnType) {
        exitMethod(className, methodName, Integer.valueOf(result), hasReturnType);
    }

    public static void exitMethod(String className, String methodName, byte result, boolean hasReturnType) {
        exitMethod(className, methodName, Byte.valueOf(result), hasReturnType);
    }

    public static void exitMethod(String className, String methodName, char result, boolean hasReturnType) {
        exitMethod(className, methodName, Character.valueOf(result), hasReturnType);
    }

    public static void exitMethod(String className, String methodName, short result, boolean hasReturnType) {
        exitMethod(className, methodName, Short.valueOf(result), hasReturnType);
    }

    public static void exitMethod(String className, String methodName, long result, boolean hasReturnType) {
        exitMethod(className, methodName, Long.valueOf(result), hasReturnType);
    }

    public static void exitMethod(String className, String methodName, float result, boolean hasReturnType) {
        exitMethod(className, methodName, Float.valueOf(result), hasReturnType);
    }

    public static void exitMethod(String className, String methodName, double result, boolean hasReturnType) {
        exitMethod(className, methodName, Double.valueOf(result), hasReturnType);
    }

    public static void exitMethod(String className, String methodName, String result, boolean hasReturnType) {
        exitMethod(className, methodName, (Object) result, hasReturnType);
    }

    public static void exitMethod(String className, String methodName, Object result, boolean hasReturnType) {
        synchronized (FastTraceLog.class) {
            if (logService == null) {
                return;
            }
            logService.exitMethod(className, methodName);
        }
    }
}
