package com.example.yang2.service.Register.impl;

import com.example.yang2.dao.UserDao;
import com.example.yang2.entity.library_user;
import com.example.yang2.service.Register.RegisterService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.service.Register.impl
 * @Description: 注册接口实现类
 * @Date: Created in 18:39 2018/6/11
 */
@Service
public class RegisterImpl implements RegisterService {

    @Resource
    private UserDao userDao;

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    @Override
    public List<library_user> checkUserExit(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 检查手机号是否已被注册
     * @param phone
     * @return
     */
    @Override
    public List<library_user> checkPhoneExit(String phone) {
        return userDao.findByPhone(phone);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void addUser(String username, String password, String sex, String phone) {

        String pass = DigestUtils.md5Hex(username + password + "homeworklibrary");

        library_user user = new library_user();
        user.setUserid(null);
        user.setUsername(username);
        user.setPassword(pass);
        user.setSex(sex);
        user.setPhone(phone);
        user.setPower("general");

        userDao.save(user);
    }
}
