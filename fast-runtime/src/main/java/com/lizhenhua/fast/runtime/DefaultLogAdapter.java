package com.lizhenhua.fast.runtime;

import android.os.Build;
import android.os.Looper;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DefaultLogAdapter extends AbstractLogAdapter {
    private static final String TAG = "FAST_LOG";
    private static final String START_STR = "├";

    @Override
    public void enterMethod(String className, String methodName, String parameterTypes, List<Object> parameterValues) {
        String message = START_STR + getThreadInfo() +
                FastTags.getMethodEnterLogTag(className, methodName, parameterTypes) +
                getMethodInfo(className, methodName, parameterTypes, parameterValues);
        Log.d(TAG, message);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            final String section = message.substring(2);
            Trace.beginSection(section);
        }
    }

    @Override
    public void exitMethod(String className, String methodName, long costTime) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Trace.endSection();
        }

        String message = START_STR + getThreadInfo() + FastTags.getMethodExitLogTag() + methodName + "() [" + costTime + "ms]";
        Log.d(TAG, message);
    }

    private String getMethodInfo(String className, String methodName, String parameterTypes, List<Object> parameterValues) {
        List<String> parameterNames = new ArrayList<>();
        parameterTypes = parameterTypes.replace("[", "").replace("]", "");
        List<String> typeList = Arrays.asList(parameterTypes.split(",", -1));
        if (!TextUtils.isEmpty(parameterTypes) && !typeList.isEmpty()) {
            for (String typeClassName : typeList) {
                if (!TextUtils.isEmpty(typeClassName)) {
                    try {
                        Class<?> cls = Class.forName(typeClassName.trim());
                        parameterNames.add(asTag(cls));
                    } catch (ClassNotFoundException ignore) {
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(getClassName(className)).append("#").append(methodName).append('(');
        for (int i = 0; i < parameterValues.size(); i++) {
            if (i > 0) {
                builder.append(", ");
            }
            if (parameterNames.size() > i) {
                builder.append(parameterNames.get(i)).append('=');
            }
            builder.append(Strings.toString(parameterValues.get(i)));
        }
        builder.append(')');
        return builder.toString();
    }

    private String getThreadInfo() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return "[main]";
        } else {
            return "[" + Thread.currentThread().getName() + "]";
        }
    }

    private String getClassName(String className) {
        if (TextUtils.isEmpty(className)) {
            return "";
        } else {
            try {
                Class<?> cls = Class.forName(className);
                return asTag(cls);
            } catch (ClassNotFoundException e) {
                return "";
            }
        }
    }
}
