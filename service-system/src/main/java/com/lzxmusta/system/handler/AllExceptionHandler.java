package com.lzxmusta.system.handler;

import com.lzxmusta.common.result.Result;
import com.lzxmusta.system.execption.LzxmustaException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName AllEx
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/10/30 20:11
 * @Version 1.0
 **/
/*
 *对@Controller中方法进行拦截
 * 统一异常处理 AOP的实现
 * */
@ControllerAdvice
public class AllExceptionHandler {
    //    处理

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result doException(Exception ex) {
        ex.printStackTrace();
        return Result.fail();
    }

    //处理特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.fail().message("执行了特定异常处理");
    }

    //添加异常处理方法
    @ExceptionHandler(LzxmustaException.class)
    @ResponseBody
    public Result error(LzxmustaException e) {
        e.printStackTrace();
        return Result.fail().message(e.getMessage()).code(e.getCode());
    }
}