package com.jediq.threadnames.spring;

import com.jediq.threadnames.ThreadName;

/**
 *
 */
public class SpringBean {

    @ThreadName
    public String nameChangingMethod(String stringParam, int intParam) {
        return Thread.currentThread().getName();
    }

    @ThreadName("stringParam={0}")
    public String nameChangingMethodWithTemplate(String stringParam, int intParam) {
        return Thread.currentThread().getName();
    }

    public String noneNameChangingMethod(String stringParam, int intParam) {
        return Thread.currentThread().getName();
    }
}
