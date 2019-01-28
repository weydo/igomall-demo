package com.igomall.config;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class EhCacheConfig {

    @Bean
    public org.springframework.cache.ehcache.EhCacheCacheManager cacheManager(){
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();

        ehCacheCacheManager.setCacheManager(ehCacheManager().getObject());
        return ehCacheCacheManager;
    }

    @Bean
    public org.springframework.cache.ehcache.EhCacheManagerFactoryBean ehCacheManager(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setShared(true);
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));

        return ehCacheManagerFactoryBean;
    }
}
