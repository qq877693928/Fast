package com.lizhenhua.fast.runtime;

import java.util.Locale;
import java.util.Stack;

public class FastTags {

  public static final Stack<String> sMethodCallStack = new Stack<>();

  public static String getMethodEnterLogTag(String className, String methodName,
      String parameterTypes) {
    sMethodCallStack.push(createMethodUniqueReference(className, methodName, parameterTypes));
    int size = sMethodCallStack.size();
    return getBlankString(size) + ">> ";
  }

  public static String getMethodExitLogTag() {
    int size = sMethodCallStack.size();
    sMethodCallStack.pop();
    return getBlankString(size) + "<< ";
  }

  private static String createMethodUniqueReference(String className, String methodName,
      String parameterTypes) {
    return String.format(Locale.US, "%s#%s(%s)", className, methodName, parameterTypes);
  }

  private static String getBlankString(int size) {
    StringBuilder stringBuilder = new StringBuilder("|");
    for (int i = 0; i < size; i++) {
      stringBuilder.append("--");
    }
    return stringBuilder.toString();
  }
}
