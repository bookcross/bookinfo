package com.trembear.bookinfo.config;

import com.trembear.bookinfo.service.RequestAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

/**
 * @author Junwei.Xiong
 * @description 资源服务器
 * @since 2018-11-27 15:53
 */
@Configuration
@EnableResourceServer
public class MyResourceConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    RequestAuthenticationService requestAuthenticationService;
    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.expressionHandler(expressionHandler);
    }
    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;
    //针对哪些path进行放行
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .and()
                .authorizeRequests()
                .antMatchers("login","oauth/**","/public").permitAll()
                .anyRequest()
                // 通过自定义requestAuthenticationService类对请求的url进行鉴权校验
                .access("@requestAuthenticationService.isPossessPermission(authentication, request)")
                //都需要身份认证
//                .authenticated()
                // 取消跨站请求防护
                .and().csrf().disable();
    }
}
