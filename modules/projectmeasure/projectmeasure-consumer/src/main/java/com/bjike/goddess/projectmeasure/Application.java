package com.bjike.goddess.projectmeasure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.*;

import java.io.IOException;

/**
 * Spring boot启动程序
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-23 19:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.projectmeasure.action","com.bjike.goddess.common.consumer","com.bjike.goddess.projectmeasure.config"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
@ImportResource({"classpath:app.xml"})
@EnableAutoConfiguration(exclude = {ValidationAutoConfiguration.class})
@PropertySource(value = {"classpath:permission.properties"},encoding="utf-8")
public class Application {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(Application.class,args);
        System.in.read();
    }

}
