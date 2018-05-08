package com.bjike.goddess.push;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;

/**
 * 扫描com.bjike.goddess.common.consumer 加入过滤器引入userToken
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.push.action", "com.bjike.goddess.push.config","com.bjike.goddess.common.consumer"})
@ImportResource("classpath:app.xml")
@EnableAutoConfiguration(exclude = {ValidationAutoConfiguration.class})
public class Application {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(Application.class, args);
        System.in.read();
    }




}
