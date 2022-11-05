package com.lzxmusta.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzxmusta.model.system.SysUser;
import com.lzxmusta.model.vo.SysUserQueryVo;

import java.util.Map;

/**
 * @ClassName SysUserService
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/5 12:13
 * @Version 1.0
 **/
public interface SysUserService extends IService<SysUser> {
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo adminQueryVo);

    void updateStatus(Long id, Integer status);

    SysUser selectUserByName(String username);

    Map<String, Object> getUserInfo(String username);
}
