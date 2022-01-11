package com.hua.restfulstarter.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    // Spring Boot加载应用程序上下文后，将运行所有CommandLineRunner bean。
    @Bean
    CommandLineRunner initDatabase() {
        // TODO: -> 的用法，函数接口？
        return args -> {
            logger.info("加载数据。。。。");
        };
    }
}
