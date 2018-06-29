package com.example.yang2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.controller
 * @Description: 退出Controller
 * @Date: Created in 19:58 2018/6/11
 */
@Controller
public class ExitController {

    @RequestMapping(value = "/exit")
    public String exit(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        return "redirect:login/";
    }

    @RequestMapping(value = "/tip")
    public ModelAndView tip(){
        return new ModelAndView("tip");
    }

}
