package com.cmcc.syw.controller;

import com.cmcc.syw.enums.Gender;
import com.cmcc.syw.model.User;
import com.cmcc.syw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by sunyiwei on 2015/11/3.
 */
@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "{count}", method = RequestMethod.POST)
    @ResponseBody
    public String insert(@PathVariable int count) {
        List<User> users = new LinkedList<User>();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setName(randStr(6));
            user.setEmail(randStr(12) + "@" + randStr(7) + ".com");
            user.setGender(randGender().getCode());

            users.add(user);
        }

        return userService.batchInsert(users) ? "OK" : "ERROR";
    }

    @RequestMapping
    public String list(ModelMap modelMap) {
        List<User> users = userService.list();
        modelMap.addAttribute("users", users);
        return "user/list";
    }

    private String randStr(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random(System.nanoTime());

        for (int i = 0; i < length; i++) {
            stringBuffer.append((char) ('a' + random.nextInt(26)));
        }

        return stringBuffer.toString();
    }

    private Gender randGender() {
        return System.nanoTime() % 2 == 0 ? Gender.MALE : Gender.FEMALE;
    }
}
