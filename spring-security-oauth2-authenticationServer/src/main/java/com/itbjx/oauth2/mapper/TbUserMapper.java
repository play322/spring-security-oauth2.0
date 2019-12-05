package com.itbjx.oauth2.mapper;

import com.itbjx.oauth2.domain.TbUser;
import tk.mybatis.mapper.MyMapper;

//public interface TbUserMapper extends MyMapper<TbUser> {
public interface TbUserMapper {
	TbUser getByUsername(String username);
}
