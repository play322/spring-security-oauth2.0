package com.itbjx.oauth2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.itbjx.oauth2.mapper"})
public class Oauth2ServerApp {
	public static void main(String[] args) {
		SpringApplication.run(Oauth2ServerApp.class,args);
	}
}
