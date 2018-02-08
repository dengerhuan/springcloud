package cn.huanuo.bigdata.config;


import cn.huanuo.bigdata.service.sys.CustomInvocationSecurityMetadataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;

/**
 * 用来做资源访问控制
 * <p>
 * 登陆后每次访问资源都会被改拦截器拦截，之后会调用，securityMetadataSource的getAttribute方法，
 * 获取被拦截资源需要的角色，之后调用accessDecisionManager类的decide方法来来判断用户时候有权限
 * <p>
 * 调度器，继承自AbstractSecurityInterceptor 是一个Filter
 * <p>
 * 包括:
 * authenticationManager, 用户鉴权管理器
 * securityMetadataSource,权限资源的元数据，关联所有的资源以及权限以及角色，即定义某一资源可以被那些角色访问
 * accessDecisionManager  访问决策们决定某个用户具有的角色，能否有权限去访问某个资源
 *
 * @author dengerhuan
 */


/**
 * Spring Security中进行身份验证的是AuthenticationManager接口，
 * ProviderManager是它的一个默认实现，但它并不用来处理身份认证，而是委托给配置好的AuthenticationProvider，
 * 每个AuthenticationProvider会轮流检查身份认证。检查后或者返回Authentication对象或者抛出异常。
 * <p>
 * 验证身份需要加载UserDetails，
 * 看看是否和用户输入的账号、密码、权限等信息匹配。
 * 此步骤由实现AuthenticationProvider的DaoAuthenticationProvider
 * （它利用UserDetailsService验证用户名、密码和授权）处理。
 */

@Component
public class MySecurityFilter extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    CustomInvocationSecurityMetadataSourceService customInvocationSecurityMetadataSourceService;


    @Autowired
    CustomAccessDecisionManager customAccessDecisionManager;


    @Autowired
    AuthenticationManager authenticationManager;


    @PostConstruct
    public void init() {
        super.setAuthenticationManager(authenticationManager);
        super.setAccessDecisionManager(customAccessDecisionManager);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("---init---");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        FilterInvocation filterInvocation = new FilterInvocation(servletRequest, servletResponse, filterChain);

        invoke(filterInvocation);
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {

        System.out.println("-----------------");

        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }

    }

    @Override
    public void destroy() {
        System.out.println("---destory---");
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.customInvocationSecurityMetadataSourceService;
    }

}
