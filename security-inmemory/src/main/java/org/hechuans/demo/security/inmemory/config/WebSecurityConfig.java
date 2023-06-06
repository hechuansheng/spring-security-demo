package org.hechuans.demo.security.inmemory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author : hechuansheng
 * @date : 2023/6/5 17:14
 * @description :
 * @since : version-1.0
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()    //授权请求
                //拥有"ROLE_student"权限的用户可以访问匹配"/student/**"的URL
                .mvcMatchers("/student/**").hasAuthority("ROLE_student")
//                .antMatchers("/student/**").hasAuthority("ROLE_student")      //ant正则匹配
//                .regexMatchers("/student/**").hasAuthority("ROLE_student")    //regex正则匹配
//                .mvcMatchers("/student/**").hasAnyAuthority("ROLE_student")   //拥有任意权限
//                .mvcMatchers("/student/**").hasRole("student")                //拥有单个角色
//                .mvcMatchers("/student/**").hasAnyRole("student")             //拥有任意角色
                //所有用户可以访问匹配"/home/**"的URL
                .mvcMatchers("/home/**").permitAll()
                .anyRequest()       //没有配置match的url
                .authenticated();    //都需要登录
//                .permitAll();        //全部放行
//                .denyAll();         //全部拒绝
//                .and()
//                .formLogin().permitAll();      //放开form表单登录登录

        //放开form表单登录登录
        http.formLogin().permitAll();

    }

}
