package cn.huanuo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.Map;

@SpringBootApplication
@EnableDiscoveryClient
public class Uaa {

    @Autowired
    RequestMappingHandlerConfig requestMappingHandlerConfig;

    public static void main(String[] args) {
        SpringApplication.run(Uaa.class, args);
    }
    @PostConstruct
    public void detectHandlerMethods() {
        final RequestMappingHandlerMapping requestMappingHandlerMapping = requestMappingHandlerConfig.requestMappingHandlerMapping();


        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();


        for (RequestMappingInfo info : map.keySet()) {
            HandlerMethod method = map.get(info);
            System.out.println(info.getPatternsCondition().toString() + method.toString());
        }

    }
}
