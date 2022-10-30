package com.lzxmusta.system;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzxmusta.model.system.SysRole;
import com.lzxmusta.system.mapper.SysRoleMapper;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SysRoleMapperTest
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/10/30 14:12
 * @Version 1.0
 **/


@SpringBootTest
public class SysRoleMapperTest {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Test
    public void testSelectList() {
        System.out.println(("----- selectAll method test ------"));
        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        //所以不填写就是无任何条件
        List<SysRole> users = sysRoleMapper.selectList(null);
        for (SysRole sysRole : users) {
            System.out.println("sysRole = " + sysRole);
        }
    }

//    mp条件构造器
    @Test
    public void selectTj(){
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        sysRoleQueryWrapper.eq("role_name","普通管理员");
        List<SysRole> sysRoles = sysRoleMapper.selectList(sysRoleQueryWrapper);
        System.out.println(sysRoles);

    }

    @Test
    public void  add(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleCode("dasds");
        sysRole.setRoleName("测试");
        sysRole.setDescription("测试角色");
        int insert = sysRoleMapper.insert(sysRole);
        System.out.println(insert);
    }
    @Test
    public  void  update(){
        SysRole sysRole = new SysRole();
        sysRole.setId("1586639642638172166");
        sysRole.setRoleCode("dasds");
        sysRole.setRoleName("测essss");
        sysRole.setDescription("测试角色");
        sysRoleMapper.updateById(sysRole);

    }

    @Test
    public  void  delete(){
        sysRoleMapper.deleteById("1586639642638172166");
    }

//    条件删除
    @Test
    public  void  testDelete(){
        QueryWrapper<SysRole> objectQueryWrapper = new QueryWrapper<SysRole>();
        objectQueryWrapper.eq("role_name","测试");
        sysRoleMapper.delete(objectQueryWrapper);

    }

//    批量删除
    @Test
    public  void deleteList(){
        sysRoleMapper.deleteBatchIds(Arrays.asList("1586639642638172164","1586639642638172165"));
    }

}
