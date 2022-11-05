package com.lzxmusta.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzxmusta.model.system.SysMenu;
import com.lzxmusta.model.system.SysUser;
import com.lzxmusta.model.vo.RouterVo;
import com.lzxmusta.model.vo.SysUserQueryVo;
import com.lzxmusta.system.mapper.SysUserMapper;
import com.lzxmusta.system.service.SysMenuService;
import com.lzxmusta.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SysUserServiceImpl
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/5 12:14
 * @Version 1.0
 **/
@Transactional
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo userQueryVo) {

        return sysUserMapper.selectPage(pageParam, userQueryVo);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        SysUser sysUser = sysUserMapper.selectById(id);
        sysUser.setStatus(status);
        sysUserMapper.updateById(sysUser);
    }

    /**
     * TODO 根据用户名查询用户数据
     *
     * @param username
     * @return SysUser
     * @Author lzxmusta刘朝旭
     * @Date 18:37 2022/11/5
     **/
    @Override
    public SysUser selectUserByName(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        return sysUser;
    }

    /**
     * TODO 根据token获取用户基本信息和权限信息
     *
     * @param username
     * @return Map<String, Object>
     * @Author lzxmusta刘朝旭
     * @Date 19:24 2022/11/5
     **/
    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> result = new HashMap<>();
        result.put("name", username);
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80");
        result.put("roles", "[\"admin\"]");
        //菜单权限数据
        LambdaQueryWrapper<SysUser> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = sysUserMapper.selectOne(userWrapper);
        //通过用户id获取菜单权限
        List<RouterVo> sysMenuList = sysMenuService.findUserMenuList(sysUser.getId());
        //通过用户id查按钮权限
        List<String> userButtonList = sysMenuService.findUserButtonList(sysUser.getId());
        //菜单权限数据
        result.put("routers",sysMenuList);
        //按钮权限数据
        result.put("buttons",userButtonList);
        return result;
    }
}
