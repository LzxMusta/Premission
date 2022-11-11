package com.lzxmusta.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzxmusta.model.system.SysLoginLog;
import com.lzxmusta.model.vo.SysLoginLogQueryVo;

/**
 * @ClassName SysLoginLogService
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/9 20:08
 * @Version 1.0
 **/

public interface SysLoginLogService  extends IService<SysLoginLog> {
    //列表显示
    IPage<SysLoginLog> selectPage(Page<SysLoginLog> pageParam, SysLoginLogQueryVo sysLoginLogQueryVo);
}
