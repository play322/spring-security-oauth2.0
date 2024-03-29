package com.itbjx.oauth2.mapper;

import com.itbjx.oauth2.domain.TbPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbPermissionMapper extends MyMapper<TbPermission> {
	List<TbPermission> selectByUserId(@Param("userId") Long userId);
}