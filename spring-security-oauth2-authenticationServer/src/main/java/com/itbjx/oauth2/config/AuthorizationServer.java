package com.itbjx.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	//配置客户端信息
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		//客户端信息存入数据库
		clients.withClientDetails(jdbcClientDetails());

		/*clients
				.inMemory()  //客户端信息存在内存中
				.withClient("c1") //客户端ID
				.secret(passwordEncoder.encode("secret"))  //客户端密码
				.authorizedGrantTypes("authorization_code","password")  //授权模式（此处是授权码模式和密码模式）
				.scopes("app")  //授权范围
				.redirectUris("http://www.baidu.com"); //验证回调函数，授权码会给到这个地址*/
	}

	//配置端点
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore());
	}

	/*配置数据源*/
	@Bean
	@Primary  //主配置
	@ConfigurationProperties(prefix = "spring.datasource") //读取配置文件的配置
	public DataSource dataSource(){
		return DataSourceBuilder.create().build();
	}

	/*
	 *	把token存入数据库
	*/
	@Bean
	public TokenStore tokenStore(){
		return new JdbcTokenStore(dataSource());
	}

	/*
	*   客户端信息存入数据库
	* */
	@Bean
	public ClientDetailsService jdbcClientDetails (){
		return  new JdbcClientDetailsService(dataSource());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
				.checkTokenAccess("permitAll()");
	}
}
