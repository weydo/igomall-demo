package com.igomall.config;

import com.igomall.entity.Admin;
import com.igomall.security.AuthenticationFilter;
import com.igomall.security.AuthorizingRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    private EhCacheManagerFactoryBean cacheManager;



    public org.apache.shiro.spring.web.ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/**","anon");



        Map<String, Filter> filters = new HashMap<>();

        AuthenticationFilter adminAuthc = new AuthenticationFilter();
        adminAuthc.setUserClass(Admin.class);
        adminAuthc.setLoginUrl("/admin/login");
        adminAuthc.setSuccessUrl("/admin/index");

        filters.put("adminAuthc",new AuthenticationFilter());
        filters.put("logout",new LogoutFilter());

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;
    }




    @Bean
    public org.apache.shiro.web.mgt.DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        defaultWebSecurityManager.setRealm(authorizingRealm());
        defaultWebSecurityManager.setCacheManager(shiroCacheManager());

        return defaultWebSecurityManager;
    }

    @Bean
    public com.igomall.security.AuthorizingRealm authorizingRealm(){
        AuthorizingRealm authorizingRealm = new AuthorizingRealm();
        authorizingRealm.setAuthorizationCacheName("authorizationCacheName");
        return authorizingRealm;
    }


    @Bean
    public org.apache.shiro.cache.ehcache.EhCacheManager shiroCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(cacheManager.getObject());

        return ehCacheManager;
    }
}
