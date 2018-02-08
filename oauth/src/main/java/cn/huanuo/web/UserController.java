package cn.huanuo.web;

import cn.huanuo.dao.sys.SysReSourceRepository;
import cn.huanuo.dao.sys.SysRoleRepository;
import cn.huanuo.pojo.entity.SysResource;
import cn.huanuo.pojo.entity.SysRole;
import cn.huanuo.pojo.entity.SysUser;
import cn.huanuo.service.sys.CustomInvocationSecurityMetadataSourceService;
import cn.huanuo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {


    @Autowired
    UserService userService;


    @Autowired
    SysReSourceRepository sysReSourceRepository;

    @Autowired
    SysRoleRepository sysRoleRepository;


    @RequestMapping("/")
    public String index() {
        return "index.html";
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

    @ResponseBody
    @GetMapping("users")
    public List<SysUser> getUsers() {
        return userService.getUsers();
    }

    @ResponseBody
    @GetMapping("resources")
    public List<SysResource> getResources() {
        return sysReSourceRepository.findAll();
    }

    @ResponseBody
    @GetMapping("roles")
    public List<SysRole> getRoles() {
        return sysRoleRepository.findAll();
    }


    @Autowired
    CustomInvocationSecurityMetadataSourceService customInvocationSecurityMetadataSourceService;

    @ResponseBody
    @GetMapping("refreshusercaches")
    public Integer refreshRoles() {
        customInvocationSecurityMetadataSourceService.loadResource();
        return 200;
    }

    @ResponseBody
    @GetMapping("/user")
    public Principal user(Principal user) {
        System.out.println(user);
        return user;
    }

}

