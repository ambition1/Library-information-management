package com.example.yang2.controller;

import com.example.yang2.entity.StatusCode;
import com.example.yang2.service.SuperAdminOperation.RootOperationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.controller
 * @Description: 超级管理员controller
 * @Date: Created in 20:50 2018/6/9
 */
@Controller
@RequestMapping(value = "/root")
public class SuperAdminOperation {

    @Resource
    private RootOperationService rootOperationService;

    @RequestMapping(value = "/adminmsglist")
    public ModelAndView adminShow(){
        return new ModelAndView("adminmsg");
    }
    @RequestMapping(value = "/adminusermsglist")
    public ModelAndView userShow(){
        return new ModelAndView("adminusermsg");
    }

    /**
     * 显示所有admin用户
     * @param pageNum
     * @param pageLimit
     * @return
     */
    @RequestMapping(value = "/adminlist")
    @ResponseBody
    public Map<String, Object> adminlist(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer pageLimit){
        Map<String, Object> adminlist = new HashMap<>();
        adminlist.put("code", 0);
        adminlist.put("msg", "");
        adminlist.put("count", rootOperationService.findAllAdminMsgCount());
        adminlist.put("data", rootOperationService.findAllAdminMsg(pageNum, pageLimit));
        return adminlist;
    }

    /**
     * 更新admin数据
     * @param userId
     * @param sex
     * @param phone
     * @return
     */
    @RequestMapping(value = "/adminmsgupdata")
    @ResponseBody
    public StatusCode updateMsg(@RequestParam(value = "userid") Integer userId, @RequestParam(value = "sex") String sex,
                                @RequestParam(value = "phone") String phone){
        StatusCode code = new StatusCode();

        try {
            rootOperationService.updateAdminMsg(userId, sex, phone);
            code.setStatuscode("700000");
            code.setStatus("数据更新成功！");
            return code;
        } catch (Exception e) {
            code.setStatuscode("700001");
            code.setStatus("数据更新失败！");
            return code;
        }
    }

    /**
     * 修改admin密码
     * @param userId
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/setpassword")
    @ResponseBody
    public String setAdminPassWord(@RequestParam(value = "userid") Integer userId,@RequestParam(value = "username") String userName,
                                   @RequestParam(value = "password") String password){
        try {
            rootOperationService.setAdminPassWord(userId, userName, password);

            return "修改成功！";
        } catch (Exception e) {
            return "修改失败！";
        }
    }

    /**
     * 删除指定admin用户
     * @param userId
     */
    @RequestMapping(value = "/deleteadmin")
    @ResponseBody
    public void deleteAdmin(@RequestParam(value = "userid") Integer userId){
        rootOperationService.deletAdminMsg(userId);
    }

    /**
     * 模糊查询admin
     * @param username
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/searchadmin")
    @ResponseBody
    public Map<String, Object> searchAdminMsg(@RequestParam(value = "username") String username,
                                              @RequestParam(value = "page", defaultValue = "1") Integer page,
                                              @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        Map<String, Object> adminMsg = new HashMap<>();
        adminMsg.put("code", 0);
        adminMsg.put("msg", "");
        adminMsg.put("count", rootOperationService.findByUserNameCount(username));
        adminMsg.put("data", rootOperationService.findByUserName(username, page, limit));
        return adminMsg;
    }

    /**
     * 添加admin用户
     * @param usename
     * @param password
     * @param sex
     * @param phone
     * @return
     */
    @RequestMapping(value = "/addadmin")
    @ResponseBody
    public StatusCode addAdminMsg(@RequestParam(value = "username") String usename, @RequestParam(value = "password") String password,
                                  @RequestParam(value = "sex") String sex, @RequestParam(value = "phone") String phone){
        StatusCode code = new StatusCode();
        try {
            rootOperationService.addAdminMsg(usename, password, sex, phone);
            code.setStatuscode("800000");
            code.setStatus("添加成功！");
            return code;
        } catch (Exception e) {
            code.setStatuscode("800001");
            code.setStatus("添加失败！");
            return code;
        }
    }

    /**
     * 展示所有user用户
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/userlist")
    @ResponseBody
    public Map<String, Object> userList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        Map<String, Object> userlist = new HashMap<>();
        userlist.put("code", 0);
        userlist.put("msg", "");
        userlist.put("count", rootOperationService.findAllUser());
        userlist.put("data", rootOperationService.findAllUser(page, limit));
        return userlist;

    }

    /**
     * 更新用户性别、手机号
     * @param userId
     * @param sex
     * @param phone
     * @return
     */
    @RequestMapping(value = "/updatausermsg")
    @ResponseBody
    public String updateUserMsg(@RequestParam(value = "userid") Integer userId, @RequestParam(value = "sex") String sex,
                                @RequestParam(value = "phone") String phone){
        try {
            rootOperationService.updateUserMsg(userId, sex, phone);
            return "更新成功！";
        } catch (Exception e) {
            return "更新失败！";
        }
    }

    /**
     * 更新用户密码
     * @param userId
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/setuserpassword")
    @ResponseBody
    public String setUserPassword(@RequestParam(value = "userid") Integer userId, @RequestParam(value = "username") String username,
                                  @RequestParam(value = "password") String password){
        try {
            rootOperationService.setUserPassword(userId, username, password);
            return "更新成功！";
        } catch (Exception e) {
            return "更新失败！";
        }
    }

    /**
     * 删除id用户
     * @param userId
     */
    @RequestMapping(value = "/deleteusermsg")
    @ResponseBody
    public void  deleteUserMsg(@RequestParam(value = "userid") Integer userId){
        rootOperationService.deleteByUserId(userId);
    }

    /**
     * 模糊查找用户
     * @param username
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/usersearch")
    @ResponseBody
    public Map<String, Object> userSearch(@RequestParam(value = "username") String username,
                                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        Map<String, Object> usersearch = new HashMap<>();
        usersearch.put("code", 0);
        usersearch.put("msg", "");
        usersearch.put("count", rootOperationService.findUserNameCount(username));
        usersearch.put("data", rootOperationService.findUserName(username, page, limit));
        return usersearch;
    }
}
