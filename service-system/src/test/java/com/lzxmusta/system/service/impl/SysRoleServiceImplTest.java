package com.lzxmusta.system.service.impl;

import com.lzxmusta.model.system.SysRole;
import com.lzxmusta.system.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SysRoleServiceImplTest {
    @Autowired
    private SysRoleService sysRoleService;
    @Test
    public void select(){
        List<SysRole> list = sysRoleService.list();

    }

}