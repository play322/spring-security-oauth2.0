package com.itbjx.oauth2.config.service;

import com.itbjx.oauth2.domain.TbPermission;
import com.itbjx.oauth2.domain.TbUser;
import com.itbjx.oauth2.service.TbPermissionService;
import com.itbjx.oauth2.service.TbUserService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private TbUserService tbUserService;

	@Autowired
	private TbPermissionService tbPermissionService;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TbUser tbUser = tbUserService.getByUsername(username); //从数据库查询用户信息
		List<GrantedAuthority> grantedAuthorities = Lists.newArrayList(); //定义一个存放用户权限的集合
		if (tbUser !=null){
			//根据用户ID查询用户的权限
			List<TbPermission> tbPermissions = tbPermissionService.selectByUserId(tbUser.getId());
			tbPermissions.forEach(tbPermission -> {
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tbPermission.getEnname());
				grantedAuthorities.add(grantedAuthority);
			});
		}
		return new User(tbUser.getUsername(),tbUser.getPassword(),grantedAuthorities);
	}
}
