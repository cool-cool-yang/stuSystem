package com.stuSystem.manager.serviceImp;


import com.stuSystem.manager.mapper.UserMapper;
import com.stuSystem.manager.pojo.User;
import com.stuSystem.manager.pojo.UserExample;
import com.stuSystem.manager.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("aserviceImp")
public class AServiceImp implements AService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User name(String name) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }
}
