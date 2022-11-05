package com.lzxmusta.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzxmusta.model.system.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SysRoleMenuMapper
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/5 15:45
 * @Version 1.0
 **/
@Repository
@Mapper
public interface SysRoleMenuMapper  extends BaseMapper<SysRoleMenu> {
}
