package com.lzxmusta.system.execption;

import com.lzxmusta.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @ClassName GuiguException
 * @Description TODO 自定义全局异常类
 * @Author lzxmusta刘朝旭
 * @Date 2022/10/30 20:15
 * @Version 1.0
 **/
@Data
public class LzxmustaException extends RuntimeException{

    private Integer code;

    private String message;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param code
     * @param message
     */
    public LzxmustaException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public LzxmustaException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "LzxmustaException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }

}
