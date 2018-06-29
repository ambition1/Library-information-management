package com.example.yang2.service.Register;

import com.example.yang2.entity.library_user;

import java.util.List;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.service.Register
 * @Description: 用户注册接口类
 * @Date: Created in 18:34 2018/6/11
 */
public interface RegisterService{

    /**
     * 检查注册用户是否存在
     * @param username
     * @return
     */
    List<library_user> checkUserExit(String username);

    /**
     * 检查手机号是否已存在
     * @param phone
     * @return
     */
    List<library_user> checkPhoneExit(String phone);

    /**
     * 添加新用户
     * @param username
     * @param password
     * @param sex
     * @param phone
     */
    void addUser(String username, String password, String sex, String phone);

}
