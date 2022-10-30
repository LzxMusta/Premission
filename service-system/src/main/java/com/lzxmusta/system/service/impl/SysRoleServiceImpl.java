package com.lzxmusta.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzxmusta.model.system.SysRole;
import com.lzxmusta.model.vo.SysRoleQueryVo;
import com.lzxmusta.system.mapper.SysRoleMapper;
import com.lzxmusta.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysRoleServiceImpl
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/10/30 17:16
 * @Version 1.0
 **/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    //角色分页查询
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> sysRolePage, SysRoleQueryVo roleQueryVo) {
        IPage<SysRole>  iPage=sysRoleMapper.findPage(sysRolePage,roleQueryVo);
        return iPage;
    }
}
