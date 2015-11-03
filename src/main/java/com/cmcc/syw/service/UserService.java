package com.cmcc.syw.service;

import com.cmcc.syw.model.User;

import java.util.List;

/**
 * Created by sunyiwei on 2015/11/3.
 */
public interface UserService {
    boolean batchInsert(List<User> users);

    List<User> list();
}
