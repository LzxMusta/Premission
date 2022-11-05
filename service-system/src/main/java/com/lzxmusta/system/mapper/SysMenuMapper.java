package com.lzxmusta.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzxmusta.model.system.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName SysMenuMapper
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/5 14:59
 * @Version 1.0
 **/
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> findListByUserId(@Param("userId") String userId);

}
