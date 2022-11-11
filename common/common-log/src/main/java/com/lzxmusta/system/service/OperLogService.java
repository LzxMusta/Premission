package com.lzxmusta.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzxmusta.model.system.SysOperLog;
import com.lzxmusta.model.vo.SysOperLogQueryVo;

/**
 * @ClassName OperLogService
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/9 21:13
 * @Version 1.0
 **/
public interface OperLogService{

    void saveSysLog(SysOperLog sysOperLog);

//    IPage<SysOperLog> selectPage(Page<SysOperLog> pageParam, SysOperLogQueryVo sysOperLogQueryVo);
}
