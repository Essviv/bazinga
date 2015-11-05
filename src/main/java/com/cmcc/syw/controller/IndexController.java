package com.cmcc.syw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sunyiwei on 2015/11/3.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping
    public String index() {
        return "index";
    }
}
