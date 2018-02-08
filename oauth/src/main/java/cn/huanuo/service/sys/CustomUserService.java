package cn.huanuo.service.sys;

import cn.huanuo.dao.sys.SysUserRepository;
import cn.huanuo.pojo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 登陆验证功能，定义来实现UserDetailsService接口，并实现其loadUserByUsername方法
 * loadUserByUsername 根据用户名来获得用户详细信息包括（权限信息）
 * 本例中 SysUser 实现了UserDetails接口
 * <p>
 * 也可以直线AuthenticationProvide 重写authenticate
 *
 * @author dengerhuan
 */
@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        SysUser user = sysUserRepository.findByUsername(s);
        System.out.println(user);

        if (user == null)
            throw new UsernameNotFoundException("user does not exist");
        System.out.println(user.getRoles());
        return user;
    }
}
