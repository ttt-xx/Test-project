package com.ttt.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     * @return 创建
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        //第一种方法
        /*filterMap.put("/add", "authc");
        filterMap.put("/update", "authc");*/
        //第二种方法
        filterMap.put("/ThymeleafDemo", "anon");
        filterMap.put("/login", "anon");

        //授权过滤器
        filterMap.put("/add", "perms[user:add]");

        filterMap.put("/*", "authc");
        //设置登录方法
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     * @return 创建
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSessionManager(@Qualifier("UserRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;

    }

    /**
     * 创建Realm
     * 放入spring环境
     *
     * @return 创建
     */
    @Bean("UserRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }
}
