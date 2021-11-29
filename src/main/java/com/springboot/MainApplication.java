package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication //springboot的全局的自动配置注解
//@MapperScan(value = )
public class MainApplication {

    public static void main(String[] args) {
        // 固定的代码 启动springboot程序 初始化spring容器
        SpringApplication.run(MainApplication.class, args);
    }

}