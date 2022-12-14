package com.lzxmusta.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzxmusta.common.result.Result;
import com.lzxmusta.model.system.SysRole;
import com.lzxmusta.model.vo.AssginRoleVo;
import com.lzxmusta.model.vo.SysRoleQueryVo;
import com.lzxmusta.system.annotation.Log;
import com.lzxmusta.system.enums.BusinessType;
import com.lzxmusta.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SysRoleController
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/10/30 17:35
 * @Version 1.0
 **/
@Api(tags = "角色管理")
//Swagger2的注解
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
//    @Log(title = "角色管理", businessType = BusinessType.SELECT)
    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId) {
        Map<String, Object> roleMap = sysRoleService.getRolesByUserId(userId);
        return Result.ok(roleMap);
    }
    @Log(title = "角色管理", businessType = BusinessType.OTHER)
    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @ApiOperation(value = "新增角色")
    @PostMapping("/save")
    public Result save(@RequestBody SysRole role) {
        sysRoleService.save(role);
        return Result.ok();
    }
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @ApiOperation(value = "修改角色")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysRole role) {
        sysRoleService.updateById(role);
        return Result.ok();
    }
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation(value = "删除角色")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") Long id) {
        sysRoleService.removeById(id);
        return Result.ok();
    }
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        sysRoleService.removeByIds(idList);
        return Result.ok();
    }
//    @Log(title = "角色管理", businessType = BusinessType.SELECT)
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("获取角色")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id) {
        SysRole role = sysRoleService.getById(id);
        return Result.ok(role);
    }
//    @Log(title = "角色管理", businessType = BusinessType.SELECT)
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("条件分页查询")
    @GetMapping("/{page}/{limit}")
    public Result findPage(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Long page,
            @ApiParam(name = "limit", value = "每页数量", required = true)
            @PathVariable("limit") Long limit,
            @ApiParam(name = "roleQueryVo", value = "查询对象", required = false)
                    SysRoleQueryVo roleQueryVo) {
        Page<SysRole> sysRolePage = new Page<>(page, limit);
        IPage<SysRole> pages = sysRoleService.selectPage(sysRolePage, roleQueryVo);
        return Result.ok(pages);
    }
//    @Log(title = "角色管理", businessType = BusinessType.SELECT)
    // TODO 查询所有角色
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("查询所有角色接口")
    //Swagger2的方法注解
    @GetMapping("findAll")
    public Result findAll() {
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }


}
