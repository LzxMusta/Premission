package com.lzxmusta.system.service;

/**
 * @ClassName AsyncLoginLogService
 * @Description TODO 异步调用日志服务
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/9 19:58
 * @Version 1.0
 **/
public interface AsyncLoginLogService {

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status 状态
     * @param ipaddr ip
     * @param message 消息内容
     * @return
     */
    void recordLoginLog(String username, Integer status, String ipaddr, String message);
}
