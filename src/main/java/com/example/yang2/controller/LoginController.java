package com.example.yang2.controller;

import com.example.yang2.entity.StatusCode;
import com.example.yang2.service.Code.ValidateCode;
import com.example.yang2.service.login.LoginCheckService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.controller
 * @Description: 登录控制类
 * @Date: Created in 21:01 2018/6/3
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginCheckService loginCheckService;

    /**
     * 显示登录页
     *
     * @return
     */
    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request) {
        System.out.println(request.getSession().getAttribute("username"));
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    /**
     * 初始化验证码
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_LOWER, 4, null);
        request.getSession().setAttribute("validateCode", verifyCode);
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = ValidateCode.generateImageCode(verifyCode, 98, 34, 5, true, Color.WHITE, Color.BLUE, null);
        try {
            ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 刷新验证码
     *
     * @param data
     * @param request
     * @param response
     */
    @RequestMapping(value = "/ValiateCoding")
    public void creatValidateCode(@RequestParam("data") String data, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Cathe-Control", "no-cathe");
        response.setContentType("image/jpeg");
        Integer codeType = Integer.parseInt(data);
        String code = ValidateCode.generateTextCode(codeType, 4, null);
        request.getSession().setAttribute("validateCode", code);
        BufferedImage bufferedImage = ValidateCode.generateImageCode(code, 98, 34, 5, true, Color.WHITE, Color.BLUE, null);
        try {
            ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录判断
     *
     * @param username
     * @param password
     * @param validCode
     * @param power
     * @param request
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public StatusCode login(@RequestParam("username") String username, @RequestParam("password") String password,
                            @RequestParam("validCode") String validCode, @RequestParam("power") String power,
                            HttpServletRequest request) {

        String pass = DigestUtils.md5Hex(username + password + "homeworklibrary");
        String code = (String) request.getSession().getAttribute("validateCode");
        StatusCode statusCode = new StatusCode();
        if (!code.equals(validCode)) {

            statusCode.setStatuscode("100000");
            statusCode.setStatus("验证码错误！");
            return statusCode;
        }

        /*
        管理员登录验证
         */
        if ("admin".equals(power)){
            if (loginCheckService.checkAdminLogin(username, pass)){
                statusCode.setStatuscode("admin");
                statusCode.setStatus("/admin/list");
                request.getSession().setAttribute("username",username);
                request.getSession().setAttribute("power",loginCheckService.findAdminPower(username));
                return statusCode;
            }else {

                statusCode.setStatuscode("100001");
                statusCode.setStatus("用户名或密码错误！");
                return statusCode;
            }
        }

        if (loginCheckService.checkUserLogin(username, pass)) {
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("power", "general");
            statusCode.setStatuscode("200");
            statusCode.setStatus("登录成功！");
            return statusCode;
        } else {

            statusCode.setStatuscode("100001");
            statusCode.setStatus("用户名或密码错误！");
            return statusCode;
        }
    }

}
