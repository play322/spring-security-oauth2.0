package com.itbjx.oauth2.service.impl;


import com.itbjx.oauth2.domain.TbUser;
import com.itbjx.oauth2.mapper.TbUserMapper;
import com.itbjx.oauth2.service.TbUserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Resource
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser getByUsername(String username) {
        return tbUserMapper.getByUsername(username);
    }

    /*@Override
    public TbUser getByUsername(String username) {
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo("username", username);
        return tbUserMapper.selectOneByExample(example);
    }*/
}
