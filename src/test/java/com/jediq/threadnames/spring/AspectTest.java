package com.jediq.threadnames.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 */
public class AspectTest {

    @Test
    public void testWithoutUsingTemplate() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        SpringBean springBean = applicationContext.getBean(SpringBean.class);

        String originalThreadName = Thread.currentThread().getName();

        assertThat(springBean.noneNameChangingMethod("myString", 256), is(originalThreadName));
        assertThat(springBean.nameChangingMethod("myString", 256), is("Param[0]=\"myString\" Param[1]=\"256\""));
        assertThat(springBean.noneNameChangingMethod("myString", 256), is(originalThreadName));
        assertThat(Thread.currentThread().getName(), is(originalThreadName));
    }

    @Test
    public void testWithUsingTemplate() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        SpringBean springBean = applicationContext.getBean(SpringBean.class);

        String originalThreadName = Thread.currentThread().getName();

        assertThat(springBean.noneNameChangingMethod("myString", 256), is(originalThreadName));
        assertThat(springBean.nameChangingMethodWithTemplate("stringParam=myString", 256), is(not(originalThreadName)));
        assertThat(springBean.noneNameChangingMethod("myString", 256), is(originalThreadName));
        assertThat(Thread.currentThread().getName(), is(originalThreadName));
    }


}
