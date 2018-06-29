package com.example.yang2.controller;

import com.example.yang2.dao.AdminDao;
import com.example.yang2.dao.BookDao;
import com.example.yang2.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.controller
 * @Description:
 * @Date: Created in 20:58 2018/6/3
 */

@Controller
@RequestMapping(value = "/")
public class HelloController {

    @Resource
    private UserDao userDao;
    @Resource
    private BookDao bookDao;

    @Resource
    private AdminDao adminDao;



    @RequestMapping(value = "/test")
    @ResponseBody
    public String jump() {
        return adminDao.adminLogin("15646168416");
    }

    @RequestMapping(value = "/test1")
    @ResponseBody
    public String show() {
        return userDao.checkuserlogin("admin");

    }

}
