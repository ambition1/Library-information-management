package com.example.yang2.controller;

import com.example.yang2.entity.StatusCode;
import com.example.yang2.service.UserOperation.UserOperationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.controller
 * @Description: 用户操作controller
 * @Date: Created in 19:37 2018/6/6
 */
@Controller
@RequestMapping(value = "/user")
public class UserOperation {

    @Resource
    private UserOperationService userOperationService;

    /**
     * 用户借书操作
     * @param bookid
     * @param bookname
     * @param request
     * @return
     */
    @RequestMapping("/borrow")
    @ResponseBody
    public StatusCode userBorrow(@RequestParam(value = "bookid") Integer bookid,
                                 @RequestParam(value = "bookname") String bookname,
                                 HttpServletRequest request) {
        StatusCode statusCode = new StatusCode();
        try {
            String username = (String) request.getSession().getAttribute("username");
            userOperationService.userBorrow(bookid, username, bookname);
            statusCode.setStatuscode("600000");
            statusCode.setStatus("借阅成功，请留意归还时间！");
            return statusCode;
        } catch (Exception e) {
            statusCode.setStatuscode("600001");
            statusCode.setStatus("借阅失败，请重试！");
            return statusCode;
        }
    }

    /**
     * 用户借阅图书续借
     * @param id
     * @param back
     * @return
     */
    @RequestMapping(value = "/renew")
    @ResponseBody
    public StatusCode userRenew(@RequestParam(value = "id") Integer id,
                                @RequestParam(value = "back") String back){
        StatusCode code = new StatusCode();

        if (!userOperationService.userIsRenew(id)){
            code.setStatuscode("600003");
            code.setStatus("您已续借，无法再次进行续借！");
            return code;
        }
        try {
            userOperationService.userRenew(id, back);
            code.setStatuscode("600005");
            code.setStatus("续借成功！");
            return code;
        } catch (Exception e) {
            code.setStatuscode("600004");
            code.setStatus("续借失败！");
            return code;
        }
    }

}
