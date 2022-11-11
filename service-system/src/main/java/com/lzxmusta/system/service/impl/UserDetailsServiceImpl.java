package com.lzxmusta.system.service.impl;

import com.lzxmusta.model.system.SysUser;
import com.lzxmusta.system.custom.CustomUser;
import com.lzxmusta.system.service.SysMenuService;
import com.lzxmusta.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description TODO `AuthenticationManager`校验所调用的三个组件之 UserDetailsService
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/6 17:43
 * @Version 1.0
 **/
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.selectUserByName(username);
        if (null == sysUser) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        if (sysUser.getStatus().intValue() == 0) {
            throw new RuntimeException("账号已停用");
        }
        List<String> userButtonList = sysMenuService.findUserButtonList(sysUser.getId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String button : userButtonList) {
            authorities.add(new SimpleGrantedAuthority(button.trim()));
        }
        return new CustomUser(sysUser, authorities);
    }
}
