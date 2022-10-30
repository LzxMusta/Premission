package com.lzxmusta.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzxmusta.model.system.SysRole;
import com.lzxmusta.model.vo.SysRoleQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SysRoleMapper
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/10/30 14:09
 * @Version 1.0
 **/
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
    IPage<SysRole> findPage(Page<SysRole> sysRolePage,@Param("roleQueryVo") SysRoleQueryVo roleQueryVo);
}
