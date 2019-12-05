package com.itbjx.oauth2.resource;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.itbjx.oauth2.resource.mapper"})
public class ResourceApp {
	public static void main(String[] args) {
		SpringApplication.run(ResourceApp.class,args);
	}
}
