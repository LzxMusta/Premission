package com.lzxmusta.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzxmusta.model.system.SysLoginLog;
import com.lzxmusta.model.vo.SysLoginLogQueryVo;
import com.lzxmusta.system.mapper.SysLoginLogMapper;
import com.lzxmusta.system.service.SysLoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName SysLoginLogServiceImpl
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/9 20:09
 * @Version 1.0
 **/
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {
    //为什么用@Resource？？？？
    @Resource
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public IPage<SysLoginLog> selectPage(Page<SysLoginLog> pageParam, SysLoginLogQueryVo sysLoginLogQueryVo) {
        return sysLoginLogMapper.selectPage1(pageParam, sysLoginLogQueryVo);
    }
}
