package com.lizhenhua.fast.runtime;

import java.util.Locale;
import java.util.Stack;

public class FastTags {

  public static final Stack<Pair<Long, String>> sMethodCallStack = new Stack<>();

  public static String getMethodEnterLogTag(String className, String methodName, long enterTimeMillis,
                                            String parameterTypes) {
    sMethodCallStack.push(Pair.create(enterTimeMillis, createMethodUniqueReference(className, methodName, parameterTypes)));
    int size = sMethodCallStack.size();
    return getBlankString(size) + ">> ";
  }

  public static Pair<Long, String> getMethodExitLogTag() {
    int size = sMethodCallStack.size();
    Pair<Long, String> pair = sMethodCallStack.pop();
    return Pair.create(pair.first, getBlankString(size) + "<< ");
  }

  private static String createMethodUniqueReference(String className, String methodName,
      String parameterTypes) {
    return String.format(Locale.US, "%s#%s(%s)", className, methodName, parameterTypes);
  }

  private static String getBlankString(int size) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < size; i++) {
      stringBuilder.append("--");
    }
    return stringBuilder.toString();
  }
}
