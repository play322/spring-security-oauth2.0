package com.itbjx.oauth2.config;

import com.itbjx.oauth2.config.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService (){
		return  new UserDetailsServiceImpl();
	}

	//配置用户
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		//配置两个用户暂时放入内存中
		auth.inMemoryAuthentication()
				.withUser("admin").password(passwordEncoder().encode("111")).roles("ADMIN")
				.and()
				.withUser("user").password(passwordEncoder().encode("222")).roles("USER");*/

		//从数据库获取用户信息
		auth.userDetailsService(userDetailsService());


	}

	
	/*密码编码器
	    不指定会报错：
	        java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
	*/
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/*@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/oauth/check_token");
	}*/
}
