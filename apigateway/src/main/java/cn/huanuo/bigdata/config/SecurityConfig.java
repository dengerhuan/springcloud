package cn.huanuo.bigdata.config;


import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  super.configure(http);
        http.csrf().disable();
        http.requestMatchers().antMatchers("/hystrix.stream/**", "/info", "/error", "/uaa/**");
    }

   /* @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers();//必须要
    }*/
}
