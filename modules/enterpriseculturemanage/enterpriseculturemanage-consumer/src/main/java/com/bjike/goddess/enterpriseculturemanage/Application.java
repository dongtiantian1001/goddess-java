package com.bjike.goddess.enterpriseculturemanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.*;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.enterpriseculturemanage.action","com.bjike.goddess.enterpriseculturemanage.config","com.bjike.goddess.common.consumer"},
		excludeFilters = {@ComponentScan.Filter(
				type = FilterType.ANNOTATION,
				value = {Configuration.class})})
@PropertySource(value = {"classpath:permission.properties"},encoding="utf-8")
@ImportResource({"classpath:app.xml"})
public class Application {


	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("800MB");
		factory.setMaxRequestSize("800MB");
		return factory.createMultipartConfig();
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class,args);
		System.in.read();
	}

}

