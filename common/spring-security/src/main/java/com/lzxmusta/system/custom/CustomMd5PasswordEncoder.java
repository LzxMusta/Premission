package com.lzxmusta.system.custom;

import com.lzxmusta.common.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @ClassName CustomMd5PasswordEncoder
 * @Description TODO `AuthenticationManager`校验所调用的三个组件之 **`PasswordEncoder`**
 * @Author lzxmusta刘朝旭
 * @Date 2022/11/6 17:28
 * @Version 1.0
 **/
@Component
public class CustomMd5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return MD5.encrypt(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(MD5.encrypt(charSequence.toString()));
    }
}
