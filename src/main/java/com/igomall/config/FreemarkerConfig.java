package com.igomall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

@Configuration
public class FreemarkerConfig {

    public org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();

        return freeMarkerConfigurer;
    }
}
