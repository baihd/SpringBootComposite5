package com.composite.controller;

import com.composite.config.DataSourceTest;
import com.composite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    DataSourceTest dataSourceTest;

    @ResponseBody
    @RequestMapping(value = "/hello")
    public Map<String, Object> testUser(Map<String, Object> params) {
        try {
            dataSourceTest.getPassword();
            userService.insert(params);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}