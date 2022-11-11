package com.lzxmusta.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzxmusta.model.system.SysOperLog;
import com.lzxmusta.model.vo.SysOperLogQueryVo;

/**
 * @ClassName SysOperLogService
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/10 2:13
 * @Version 1.0
 **/
public interface SysOperLogService  extends IService<SysOperLog> {

    IPage<SysOperLog> selectPage1(Page<SysOperLog> pageParam, SysOperLogQueryVo sysOperLogQueryVo);

    SysOperLog getById1(Long id);
}
