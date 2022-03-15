package com.lizhenhua.fast.runtime;

import java.util.List;

public abstract class AbstractLogAdapter {
    protected abstract void enterMethod(String className, String methodName, String parameterTypes, List<Object> parameterValues);

    protected abstract void exitMethod(String className, String methodName);

    protected String asTag(Class<?> cls) {
        if (cls.isAnonymousClass()) {
            return asTag(cls.getEnclosingClass());
        }
        return cls.getSimpleName();
    }
}
