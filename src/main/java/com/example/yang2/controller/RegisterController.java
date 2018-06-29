package com.example.yang2.controller;

import com.example.yang2.entity.StatusCode;
import com.example.yang2.service.Register.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.controller
 * @Description: 注册controller
 * @Date: Created in 18:27 2018/6/11
 */

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Resource
    private RegisterService registerService;

    @RequestMapping(value = "/")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    /**
     * 注册
     * @param username
     * @param password
     * @param repassword
     * @param sex
     * @param phone
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public StatusCode userRegister(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
                                   @RequestParam(value = "repassword") String repassword,
                                   @RequestParam(value = "sex") String sex, @RequestParam(value = "phone") String phone){
        StatusCode code = new StatusCode();

        /*
        校验密码
         */
        if (!repassword.equals(password)){
            code.setStatuscode("200003");
            code.setStatus("两次密码不一致！");
            return code;
        }

        /*
        用户名查重
         */
        if (!(registerService.checkUserExit(username).isEmpty())){
            code.setStatuscode("200001");
            code.setStatus("该用户名已被注册，请直接登陆！");
            return code;
        }

        /*
        手机号查重
         */
        if (!(registerService.checkPhoneExit(phone).isEmpty())){
            code.setStatuscode("200002");
            code.setStatus("该手机号已被注册，请直接登陆！");
            return code;
        }

        try {
            registerService.addUser(username, password, sex, phone);
            code.setStatuscode("200000");
            code.setStatus("注册成功！");
            return code;
        } catch (Exception e) {
            code.setStatuscode("200004");
            code.setStatus("网络繁忙，请重试！");
            return code;
        }
    }

}
