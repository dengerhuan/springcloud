package cn.huanuo.bigdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class RestCon {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("name")
    public String name() {
        return "dengerhuan";
    }

    @GetMapping("ss")
    public String ss() {

        return "dengerhuan";
    }

    @GetMapping("/user")
    public Principal user(Principal user) {
        System.out.println(user);
        return user;
    }

    @GetMapping("/instance-info")
    public ServiceInstance showInfo() {
        ServiceInstance localServiceInstance = this.discoveryClient.
                getLocalServiceInstance();
        return localServiceInstance;
    }
}
