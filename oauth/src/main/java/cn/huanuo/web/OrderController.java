package cn.huanuo.web;

import cn.huanuo.dao.sys.SysReSourceRepository;
import cn.huanuo.dao.sys.SysRoleRepository;
import cn.huanuo.pojo.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class OrderController {


    public static final int id = 10;

    @GetMapping("t1")
    public String t1() {
        //for debug
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("t2")
    public String t2() {
        //for debug
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }


    @Autowired
    SysReSourceRepository sysReSourceRepository;

    @Autowired
    SysRoleRepository sysRoleRepository;


    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }


    @GetMapping("/loadresource")
    @Deprecated
    public Map<String, Collection<ConfigAttribute>> loadResource() {

        List<SysRole> list = sysRoleRepository.findAll();
        Map<String, Collection<ConfigAttribute>> resourceMap = null;
        resourceMap = new HashMap<>();
/*

        if (list != null && list.size() > 0) {
            for (SysRole sysRole : list) {

                ConfigAttribute ca = new SecurityConfig(sysRole.getName());
                List<SysResource> resources = sysRole.getResources();
                if (resources != null && resources.size() > 0) {
                    for (SysResource resource : resources) {
                        String url = resource.getUrl();

                        if (resourceMap.containsKey(url)) {
                            Collection<ConfigAttribute> roles = new ArrayList<>();
                            roles.add(ca);

                        } else {
                            Collection<ConfigAttribute> roles = new ArrayList<>();
                            roles.add(ca);
                            resourceMap.put(url, roles);
                        }

                    }
                }
            }
        }
*/

        return resourceMap;
    }
}
