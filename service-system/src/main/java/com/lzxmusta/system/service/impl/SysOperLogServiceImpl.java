package com.lzxmusta.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzxmusta.model.system.SysOperLog;
import com.lzxmusta.model.vo.SysOperLogQueryVo;
import com.lzxmusta.system.mapper.SysOperLogMapper;
import com.lzxmusta.system.service.SysOperLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @ClassName SysOperLogServiceImpl
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/10 2:14
 * @Version 1.0
 **/


@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper,SysOperLog> implements SysOperLogService {
    @Resource
    private SysOperLogMapper sysOperLogMapper;

    @Override
    public IPage<SysOperLog> selectPage1(Page<SysOperLog> pageParam, SysOperLogQueryVo sysOperLogQueryVo) {
        IPage<SysOperLog> page = sysOperLogMapper.selectPage1(pageParam, sysOperLogQueryVo);
        return page;
    }

    @Override
    public SysOperLog getById1(Long id) {
        SysOperLog sysOperLog = sysOperLogMapper.selectById(id);
        return sysOperLog;
    }
}
