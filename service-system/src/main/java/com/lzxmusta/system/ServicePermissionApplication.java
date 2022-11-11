package com.lzxmusta.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @ClassName ServicePermissionApplication
 * @Description TODO
 * @Author lzxmusta刘朝旭
 * @Date 2022/10/30 13:30
 * @Version 1.0
 **/

@SpringBootApplication
@MapperScan(basePackages ="com.lzxmusta.system.mapper")
public class ServicePermissionApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ServicePermissionApplication.class, args);
    }
}
