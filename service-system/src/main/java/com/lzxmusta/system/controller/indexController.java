package com.lzxmusta.system.controller;

import com.lzxmusta.common.result.Result;
import com.lzxmusta.common.result.ResultCodeEnum;
import com.lzxmusta.common.utils.JwtHelper;
import com.lzxmusta.common.utils.MD5;
import com.lzxmusta.common.utils.ResponseUtil;
import com.lzxmusta.model.system.SysUser;
import com.lzxmusta.model.vo.LoginVo;
import com.lzxmusta.system.execption.LzxmustaException;
import com.lzxmusta.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/5 10:23
 * @Version 1.0
 **/
@Api(tags = "登录接口")
@RestController
@RequestMapping("/admin/system/index")
public class indexController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * TODO 登录
     *
     * @param
     * @return Result
     * @Author lzxmusta刘朝旭
     * @Date 10:35 2022/11/5
     **/
    @PostMapping("/login")
    public Result userLogin(@RequestBody LoginVo loginVo) {

        // 1.根据用户名称查询数据
        SysUser sysUser = sysUserService.selectUserByName(loginVo.getUsername());
        // 2.如果为空 不可登录
        if (sysUser == null) {
            throw new LzxmustaException(20001, "用户不存在");
        }
        //3.判断密码是否一致
        String s = MD5.encrypt(loginVo.getPassword());
        if (!sysUser.getPassword().equals(s)) {
            throw new LzxmustaException(20002, "密码错误");
        }
        // 4.判断用户是否可用
        if (sysUser.getStatus() == 0) {
            throw new LzxmustaException(20003, "用户已禁用");
        }
        //5.根据id name 生成token
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        // 6.返回
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return Result.ok(map);
    }

    @GetMapping("/info")
    public Result info(HttpServletRequest request) {
//获取请求头token字符串
        String token=request.getHeader("token");
        //从token中获取用户名称和id
        String username = JwtHelper.getUsername(token);
        //根据用户名称获取用户基本信息和权限数据
        Map<String, Object> map = sysUserService.getUserInfo(username);
        return Result.ok(map);
    }

    /**
     * TODO 退出
     *
     * @param
     * @return Result
     * @Author lzxmusta刘朝旭
     * @Date 10:36 2022/11/5
     **/
    @PostMapping("/logout")
    public Result logout() {
        return Result.ok();
    }
}
