package cn.huanuo.bigdata.config.oauth2;

import cn.huanuo.bigdata.config.MySecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


    @Autowired
    MySecurityFilter mySecurityFilter;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        //super.configure(http);


        http.addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class);
        http.requestMatchers().anyRequest().and().authorizeRequests().
                antMatchers("xxxxx/**").authenticated();


        // @formatter:off
 /*       http
                // Since we want the protected resources to be accessible in the UI as well we need
                // session creation to be allowed (it's disabled by default in 2.0.6)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .anonymous()
                .and()
                .authorizeRequests().anyRequest().authenticated();//配置order访问控制，必须认证过后才可以访问
        // @formatter:on*/
    }

}
