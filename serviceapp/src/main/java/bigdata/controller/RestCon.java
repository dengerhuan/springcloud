package bigdata.controller;

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

    @GetMapping("/user")
    public String user(Principal user) {
        String name = user.getName();
        return name;
    }
}
