package org.hechuans.demo.security.inmemory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author : hechuansheng
 * @date : 2023/6/5 15:34
 * @description :
 * @since : version-1.0
 */
@SpringBootApplication
//开启方法权限控制
@EnableGlobalMethodSecurity
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
