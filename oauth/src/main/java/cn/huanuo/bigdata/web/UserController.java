package cn.huanuo.bigdata.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @RequestMapping("/")
    public String index() {
        return "/index.html";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hellopage.html";
    }

    @RequestMapping("/home")
    public String home() {
        return "homepage.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "loginpage.html";
    }
}
