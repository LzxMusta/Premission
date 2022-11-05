package com.lzxmusta.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzxmusta.model.system.SysMenu;
import com.lzxmusta.model.vo.AssginMenuVo;
import com.lzxmusta.model.vo.RouterVo;
import com.lzxmusta.system.mapper.SysMenuMapper;

import java.util.List;

/**
 * @ClassName SysMenuService
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/5 14:59
 * @Version 1.0
 **/
public interface SysMenuService extends IService<SysMenu> {


    /**
     * 用户id查按钮权限
     * @return
     */
    List<String> findUserButtonList(String id);

    /**
     * 用户id查菜单权限
     * @return
     */
     List<RouterVo> findUserMenuList(String id);

    /**
     * 菜单树形数据
     * @return
     */
    List<SysMenu> findNodes();
    /**
     * 根据角色获取授权权限数据
     * @return
     */
    List<SysMenu> findSysMenuByRoleId(Long roleId);

    /**
     * 保存角色权限
     * @param  assginMenuVo
     */
    void doAssign(AssginMenuVo assginMenuVo);

}
