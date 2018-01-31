package cn.huanuo.bigdata.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {


        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        Object accessToken = request.getParameter("access_token");


        if (accessToken == null) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            requestContext.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            requestContext.set("error.exception", new RuntimeException("AccessToken is null"));
            return null;
        }

        return null;
    }
}
