package com.lzxmusta.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzxmusta.model.system.SysRole;
import com.lzxmusta.model.vo.AssginRoleVo;
import com.lzxmusta.model.vo.SysRoleQueryVo;

import java.util.Map;

/**
 * @ClassName SysRoleService
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/10/30 17:15
 * @Version 1.0
 **/

public interface SysRoleService extends IService<SysRole> {
    //查询角色分页
    IPage<SysRole> selectPage(Page<SysRole> sysRolePage, SysRoleQueryVo roleQueryVo);
    /**
     * 根据用户获取角色数据
     * @param userId
     * @return
     */
    Map<String, Object> getRolesByUserId(Long userId);
    /**
     * 分配角色
     * @param assginRoleVo
     */
    void doAssign(AssginRoleVo assginRoleVo);
}
