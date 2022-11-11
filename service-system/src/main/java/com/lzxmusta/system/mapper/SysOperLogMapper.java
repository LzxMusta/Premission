package com.lzxmusta.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzxmusta.model.system.SysOperLog;
import com.lzxmusta.model.vo.SysOperLogQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SysOperLogMapper
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/9 21:30
 * @Version 1.0
 **/
@Mapper
@Repository
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
    IPage<SysOperLog> selectPage1(Page<SysOperLog> page, @Param("sysOperLogQueryVo") SysOperLogQueryVo sysOperLogQueryVo);
}
