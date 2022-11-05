package com.lzxmusta.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzxmusta.model.system.SysRole;
import com.lzxmusta.model.system.SysUserRole;
import com.lzxmusta.model.vo.AssginRoleVo;
import com.lzxmusta.model.vo.SysRoleQueryVo;
import com.lzxmusta.system.mapper.SysRoleMapper;
import com.lzxmusta.system.mapper.SysUserRoleMapper;
import com.lzxmusta.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     * 根据用户获取角色数据
     * @param userId
     * @return
     */
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Map<String, Object> getRolesByUserId(Long userId) {
        //获取所有角色
        List<SysRole> sysRoleList = sysRoleMapper.selectList(null);
        //根据用户id查询
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<SysUserRole>();
        wrapper.eq(SysUserRole::getUserId,userId);
        //获取用户已分配的角色
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectList(wrapper);
        //获取用户已分配的角色id
        List<Long> userRoleIds = new ArrayList<>();
        for (SysUserRole userRole:sysUserRoleList) {
            userRoleIds.add((userRole.getRoleId()));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("allRoles",sysRoleList);
        map.put("userRoleIds",userRoleIds);
        return map;
    }
/**
 * TODO 分配角色
 * @Author lzxmusta刘朝旭
 * @Date 14:31 2022/11/5
 * @param assginRoleVo
 * @return void
 **/
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //根据用户id删除原来分配的角色
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId,assginRoleVo.getUserId());
        sysUserRoleMapper.delete(queryWrapper);
        //获取所有的角色id
        List<Long> roleIdList = assginRoleVo.getRoleIdList();
        for (Long roleId : roleIdList) {
            if(roleId != null){
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(assginRoleVo.getUserId());
                sysUserRole.setRoleId(roleId);
                //保存
                sysUserRoleMapper.insert(sysUserRole);
            }
        }
    }

}
