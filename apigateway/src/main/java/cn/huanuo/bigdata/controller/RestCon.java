package cn.huanuo.bigdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/instance-info")
    public ServiceInstance showInfo() {
        ServiceInstance localServiceInstance = this.discoveryClient.
                getLocalServiceInstance();
        return localServiceInstance;
    }
}
