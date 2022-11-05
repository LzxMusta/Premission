package com.lzxmusta.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzxmusta.common.helper.MenuHelper;
import com.lzxmusta.common.helper.RouterHelper;
import com.lzxmusta.common.result.ResultCodeEnum;
import com.lzxmusta.model.system.SysMenu;
import com.lzxmusta.model.system.SysRoleMenu;
import com.lzxmusta.model.vo.AssginMenuVo;
import com.lzxmusta.model.vo.RouterVo;
import com.lzxmusta.system.execption.LzxmustaException;
import com.lzxmusta.system.mapper.SysMenuMapper;
import com.lzxmusta.system.mapper.SysRoleMenuMapper;
import com.lzxmusta.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysMenuServiceImpl
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/5 15:00
 * @Version 1.0
 **/
@Transactional
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 用户id查按钮权限
     *
     * @return
     */
    @Override
    public List<String> findUserButtonList(String id) {
        //超级管理员admin账号id为：1
        List<SysMenu> sysMenuList = null;
        if ("1".equals(id)) {
            //管理员查全部
            sysMenuList = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        } else {
            sysMenuList = sysMenuMapper.findListByUserId(id);
        }
        //创建返回的集合
        List<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getType() == 2) {
                permissionList.add(sysMenu.getPerms());
            }
        }
        return permissionList;
    }

    /**
     * 用户id查菜单权限
     *
     * @return
     */
    @Override
    public List<RouterVo> findUserMenuList(String id) {
        //超级管理员admin账号id为：1
        List<SysMenu> sysMenuList = null;
        if ("1".equals(id)) {
            //管理员查全部
            sysMenuList = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        } else {
            sysMenuList = sysMenuMapper.findListByUserId(id);
        }
        //构建树形结构
        List<SysMenu> sysMenuList1= MenuHelper.buildTree(sysMenuList);
        //转换为前端路由需要的格式
        List<RouterVo> routerVoList= RouterHelper.buildRouters(sysMenuList1);
        return routerVoList;
    }

    @Override
    public List<SysMenu> findNodes() {
        //全部权限列表
        List<SysMenu> sysMenuList = this.list();
        if (CollectionUtils.isEmpty(sysMenuList)) return null;

        //构建树形数据
        List<SysMenu> result = MenuHelper.buildTree(sysMenuList);
        return result;
    }

    @Override
    public boolean removeById(Serializable id) {
        int count = this.count(new QueryWrapper<SysMenu>().eq("parent_id", id));
        if (count > 0) {
            throw new LzxmustaException(ResultCodeEnum.NODE_ERROR);
        }
        sysMenuMapper.deleteById(id);
        return false;
    }

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findSysMenuByRoleId(Long roleId) {
        //获取所有status为1的权限列表
        List<SysMenu> menuList = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        //根据角色id获取角色权限
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
        //获取该角色已分配的所有权限id
        List<Long> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu roleMenu : roleMenus) {
            roleMenuIds.add(roleMenu.getMenuId());
        }
        //遍历所有权限列表
        for (SysMenu sysMenu : menuList) {
            if (roleMenuIds.contains(sysMenu.getId())) {
                //设置该权限已被分配
                sysMenu.setSelect(true);
            } else {
                sysMenu.setSelect(false);
            }
        }
        //将权限列表转换为权限树
        List<SysMenu> sysMenus = MenuHelper.buildTree(menuList);
        return sysMenus;
    }

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //删除已分配的权限
        sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id", assginMenuVo.getRoleId()));
        //遍历所有已选择的权限id
        for (Long menuId : assginMenuVo.getMenuIdList()) {
            if (menuId != null) {
                //创建SysRoleMenu对象
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
                //添加新权限
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }
    }

}
