package com.lzxmusta.system.fillter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzxmusta.common.result.Result;
import com.lzxmusta.common.result.ResultCodeEnum;
import com.lzxmusta.common.utils.IpUtil;
import com.lzxmusta.common.utils.JwtHelper;
import com.lzxmusta.common.utils.ResponseUtil;
import com.lzxmusta.model.vo.LoginVo;
import com.lzxmusta.system.custom.CustomUser;
import com.lzxmusta.system.service.AsyncLoginLogService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * @ClassName TokenLoginFilter
 * @Description TODO 自定义用户认证接口 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/6 17:51
 * @Version 1.0
 **/

public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {


    private RedisTemplate redisTemplate;

    private AsyncLoginLogService asyncLoginLogService;

    public TokenLoginFilter(AuthenticationManager authenticationManager, RedisTemplate redisTemplate,AsyncLoginLogService asyncLoginLogService) {
        this.setAuthenticationManager(authenticationManager);
        this.setPostOnly(false);
        //指定登录接口及提交方式，可以指定任意路径
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/system/index/login", "POST"));
        this.redisTemplate = redisTemplate;
        this.asyncLoginLogService=asyncLoginLogService;
    }

    /**
     * 登录认证
     *
     * @param req
     * @param res
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            LoginVo loginVo = new ObjectMapper().readValue(req.getInputStream(), LoginVo.class);

            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword());
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 登录成功
     * @param request
     * @param response
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        String token = JwtHelper.createToken(customUser.getSysUser().getId(), customUser.getSysUser().getUsername());
//        String username = customUser.getUsername();
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add(username);
        //保存权限数据
        redisTemplate.opsForValue().set(customUser.getUsername(), JSON.toJSONString(customUser.getAuthorities()));
//        System.out.println(redisTemplate.opsForValue().multiGet(strings)+"========================8708967694657235");
//        System.out.println(redisTemplate.opsForValue().get(customUser.getUsername()));
        //记录登录日志信息
        asyncLoginLogService.recordLoginLog(customUser.getUsername(),1, IpUtil.getIpAddress(request),"登录成功");
        logger.info("======登录日志记录成功======");
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        ResponseUtil.out(response, Result.ok(map));
    }


    /**
     * 登录失败
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {

        if (e.getCause() instanceof RuntimeException) {
            ResponseUtil.out(response, Result.build(null, 204, e.getMessage()));
        } else {
            ResponseUtil.out(response, Result.build(null, ResultCodeEnum.LOGIN_MOBLE_ERROR));
        }
    }

}
