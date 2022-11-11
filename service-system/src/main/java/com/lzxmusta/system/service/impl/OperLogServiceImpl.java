package com.lzxmusta.system.service.impl;

import com.lzxmusta.model.system.SysOperLog;
import com.lzxmusta.system.mapper.SysOperLogMapper;
import com.lzxmusta.system.service.OperLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName SysOperLogServiceImpl
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/9 21:15
 * @Version 1.0
 **/
@Service
public class OperLogServiceImpl implements OperLogService {
    @Resource
    private SysOperLogMapper operLogMapper;
    @Override
    public void saveSysLog(SysOperLog sysOperLog) {
        operLogMapper.insert(sysOperLog);
    }
}
