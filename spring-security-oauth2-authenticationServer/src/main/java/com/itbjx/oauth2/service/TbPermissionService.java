package com.itbjx.oauth2.service;


import com.itbjx.oauth2.domain.TbPermission;

import java.util.List;

public interface TbPermissionService {
    default List<TbPermission> selectByUserId(Long userId) {
        return null;
    }
}
