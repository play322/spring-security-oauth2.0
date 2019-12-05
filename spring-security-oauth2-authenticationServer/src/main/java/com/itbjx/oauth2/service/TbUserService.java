package com.itbjx.oauth2.service;


import com.itbjx.oauth2.domain.TbUser;

public interface TbUserService {

 /*default TbUser getByUsername(String username) {
        return null;
    }*/

    TbUser getByUsername(String username);
}
