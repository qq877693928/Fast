package com.lizhenhua.fast.annotation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({TYPE, METHOD, CONSTRUCTOR})
@Retention(CLASS)
public @interface FastLog {
    LogPolicy value() default LogPolicy.METHOD_AND_COST;
}
