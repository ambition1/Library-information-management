package com.example.yang2.service.login.impl;

import com.example.yang2.dao.AdminDao;
import com.example.yang2.dao.UserDao;
import com.example.yang2.service.login.LoginCheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.service.login.impl
 * @Description: 登录判断实现类
 * @Date: Created in 22:35 2018/6/3
 */
@Service
public class LoginCheckImpl implements LoginCheckService {

    @Resource
    private UserDao userDao;
    @Resource
    private AdminDao adminDao;

    /**
     * 检测用户登录信息
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public Boolean checkUserLogin(String username, String password) {
        try {
            return userDao.checkuserlogin(username).equals(password);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 检测admin登录信息
     * @param username
     * @param password
     * @return
     */
    @Override
    public Boolean checkAdminLogin(String username, String password) {

        try {
            return adminDao.adminLogin(username).equals(password);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查询admin权限
     * @param username
     * @return
     */
    @Override
    public String findAdminPower(String username) {
        return adminDao.findPower(username);
    }
}
