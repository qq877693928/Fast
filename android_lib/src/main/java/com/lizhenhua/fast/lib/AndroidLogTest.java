package com.lizhenhua.fast.lib;

import com.lizhenhua.fast.annotation.FastLog;

public class AndroidLogTest {

    @FastLog
    public void logLong(long value, long value2, long value3) {
        long var4 = System.currentTimeMillis();
        System.out.println(value);
    }
}
