package com.cmcc.syw.service.impl;

import com.cmcc.syw.dao.UserMapper;
import com.cmcc.syw.model.User;
import com.cmcc.syw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunyiwei on 2015/11/3.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;

    @Override
    public boolean batchInsert(List<User> users) {
        if(users==null || users.size() <= 0){
            return false;
        }

        mapper.batchInsert(users);
        return true;
    }

    @Override
    public List<User> list() {
        return mapper.list();
    }
}
