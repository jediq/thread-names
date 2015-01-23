package com.jediq.threadnames;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class ThreadNamesAspect {
    
    private Formatter formatter = new Formatter();
    private ThreadLocal<String> originalNameStore = new ThreadLocal<String>();

    @Pointcut(value = "execution(public * *(..))")
    public void anyPublicMethod() {
    }

    @Before("anyPublicMethod() && @annotation(threadNameAnnotation)")
    public void beforeMethodCall(JoinPoint pjp, ThreadName threadNameAnnotation) throws Throwable {
        Thread thread = Thread.currentThread();
        String originalThreadName = thread.getName();
        originalNameStore.set(originalThreadName);

        String template = threadNameAnnotation.value();

        String newName;
        if (template.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            int counter = 0;
            for (Object object : pjp.getArgs()) {
                builder.append(String.format("Param[%s]=\"%s\" ", ++counter, object));
            }
            newName = builder.toString().trim();
        } else {
            newName = formatter.format(template, Arrays.asList(pjp.getArgs()));
        }
        
        thread.setName(newName);
    }

    @After("anyPublicMethod() && @annotation(threadNameAnnotation)")
    public void afterMethodCall(ThreadName threadNameAnnotation) throws Throwable {
        Thread.currentThread().setName(originalNameStore.get());
    }
}