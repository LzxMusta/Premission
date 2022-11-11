package com.lzxmusta.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzxmusta.model.system.SysLoginLog;
import com.lzxmusta.model.vo.SysLoginLogQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SysLoginLogMapper
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/9 20:05
 * @Version 1.0
 **/
@Repository
@Mapper
public interface SysLoginLogMapper  extends BaseMapper<SysLoginLog> {
    IPage<SysLoginLog> selectPage1(Page<SysLoginLog> page, @Param("sysLoginLogQueryVo") SysLoginLogQueryVo sysLoginLogQueryVo);
}
