package com.lzxmusta.system.service.impl;

import com.lzxmusta.model.system.SysLoginLog;
import com.lzxmusta.system.mapper.SysLoginLogMapper;
import com.lzxmusta.system.service.AsyncLoginLogService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName AsyncLoginLogServiceImpl
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/9 19:59
 * @Version 1.0
 **/
@Service
public class AsyncLoginLogServiceImpl implements AsyncLoginLogService {

    @Resource
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public void recordLoginLog(String username, Integer status, String ipaddr, String message) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUsername(username);
        sysLoginLog.setIpaddr(ipaddr);
        sysLoginLog.setMsg(message);
        // 日志状态
        sysLoginLog.setStatus(status);
        sysLoginLogMapper.insert(sysLoginLog);
    }
}
