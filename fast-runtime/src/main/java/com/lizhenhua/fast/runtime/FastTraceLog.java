package com.lizhenhua.fast.runtime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Build;
import android.os.Looper;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;

public class FastTraceLog {

  public static void enterMethod(String className, String methodName, String parameterTypes,
      List<Object> parameterValues) {
    String tag = TextUtils.isEmpty(className) ? "" : className;
    try {
      Class<?> cls = Class.forName(className.trim());
      tag = asTag(cls);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    List<String> parameterNames = null;
    parameterTypes = parameterTypes.replace("[", "").replace("]", "");
    List<String> typeList = Arrays.asList(parameterTypes.split(",", -1));
    if (!TextUtils.isEmpty(parameterTypes) && typeList != null && typeList.size() > 0) {
      parameterNames = new ArrayList<>(typeList.size());
      for (String typeClassName : typeList) {
        try {
          Class<?> cls = Class.forName(typeClassName.trim());
          parameterNames.add(asTag(cls));
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
      }
    }

    StringBuilder builder = new StringBuilder(">>>> ");
    builder.append(methodName).append('(');
    for (int i = 0; i < parameterValues.size(); i++) {
      if (i > 0) {
        builder.append(", ");
      }
      if (parameterNames != null && parameterNames.size() > i) {
        builder.append(parameterNames.get(i)).append('=');
      }
      builder.append(Strings.toString(parameterValues.get(i)));
    }
    builder.append(')');

    if (Looper.myLooper() != Looper.getMainLooper()) {
      builder.append(" [Thread:\"").append(Thread.currentThread().getName()).append("\"]");
    }

    Log.d(tag, builder.toString());

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
      final String section = builder.toString().substring(2);
      Trace.beginSection(section);
    }
  }

  public static void exitMethod(String className, String methodName, boolean result,
      long lengthMillis, boolean hasReturnType) {
    exitMethod(className, methodName, Boolean.valueOf(result), lengthMillis, hasReturnType);
  }

  public static void exitMethod(String className, String methodName, int result,
      long lengthMillis, boolean hasReturnType) {
    exitMethod(className, methodName, Integer.valueOf(result), lengthMillis, hasReturnType);
  }

  public static void exitMethod(String className, String methodName, byte result,
      long lengthMillis, boolean hasReturnType) {
    exitMethod(className, methodName, Byte.valueOf(result), lengthMillis, hasReturnType);
  }

  public static void exitMethod(String className, String methodName, char result,
      long lengthMillis, boolean hasReturnType) {
    exitMethod(className, methodName, Character.valueOf(result), lengthMillis, hasReturnType);
  }

  public static void exitMethod(String className, String methodName, short result,
      long lengthMillis, boolean hasReturnType) {
    exitMethod(className, methodName, Short.valueOf(result), lengthMillis, hasReturnType);
  }

  public static void exitMethod(String className, String methodName, long result,
      long lengthMillis, boolean hasReturnType) {
    exitMethod(className, methodName, Long.valueOf(result), lengthMillis, hasReturnType);
  }

  public static void exitMethod(String className, String methodName, float result,
      long lengthMillis, boolean hasReturnType) {
    exitMethod(className, methodName, Float.valueOf(result), lengthMillis, hasReturnType);
  }

  public static void exitMethod(String className, String methodName, double result,
      long lengthMillis, boolean hasReturnType) {
    exitMethod(className, methodName, Double.valueOf(result), lengthMillis, hasReturnType);
  }

  public static void exitMethod(String className, String methodName, String result,
      long lengthMillis, boolean hasReturnType) {
    exitMethod(className, methodName, (Object) result, lengthMillis, hasReturnType);
  }

  public static void exitMethod(String className, String methodName, Object result,
      long lengthMillis, boolean hasReturnType) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
      Trace.endSection();
    }

    String tag = TextUtils.isEmpty(className) ? "" : className;
    try {
      Class<?> cls = Class.forName(className.trim());
      tag = asTag(cls);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    StringBuilder builder = new StringBuilder("<<<< ")
        .append(methodName)
        .append(" [")
        .append(lengthMillis)
        .append("ms]");

    if (hasReturnType) {
      builder.append(" = ");
      builder.append(Strings.toString(result));
    }

    Log.d(tag, builder.toString());
  }

  private static String asTag(Class<?> cls) {
    if (cls.isAnonymousClass()) {
      return asTag(cls.getEnclosingClass());
    }
    return cls.getSimpleName();
  }
}
