package com.jediq.threadnames.spring;

import com.jediq.threadnames.ThreadNamesAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public ThreadNamesAspect threadNamesAspect() {
        return new ThreadNamesAspect();
    }
    
    @Bean
    public SpringBean springBean() {
        return new SpringBean();
    }
}