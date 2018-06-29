package com.example.yang2.service.SuperAdminOperation.impl;

import com.example.yang2.dao.AdminDao;
import com.example.yang2.dao.UserDao;
import com.example.yang2.entity.library_admin;
import com.example.yang2.entity.library_user;
import com.example.yang2.service.SuperAdminOperation.RootOperationService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.service.SuperAdminOperation.impl
 * @Description: root操作的实现类
 * @Date: Created in 21:06 2018/6/9
 */

@Service
public class RootOperationImpl implements RootOperationService {

    @Resource
    private AdminDao adminDao;
    @Resource
    private UserDao userDao;

    /**
     * 所有admin信息
     * @param pageNum
     * @param pageLimit
     * @return
     */
    @Override
    public List<library_admin> findAllAdminMsg(Integer pageNum, Integer pageLimit) {
        return adminDao.findAllAdminMsg((pageNum-1)*pageLimit, pageLimit);
    }

    /**
     * 所有admin用的条数
     * @return
     */
    @Override
    public Integer findAllAdminMsgCount() {
        return adminDao.findAllAdminMsgCount();
    }

    /**
     * 更新admin部分信息
     * @param userId
     * @param sex
     * @param phone
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateAdminMsg(Integer userId, String sex, String phone) {
        adminDao.updateByUserId(userId, sex, phone);
    }

    /**
     * 更新admin密码
     * @param userId
     * @param userName
     * @param passWord
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void setAdminPassWord(Integer userId, String userName, String passWord) {

        String pass = DigestUtils.md5Hex(userName + passWord + "homeworklibrary");

        adminDao.setAdminPassWord(userId, pass);
    }

    /**
     * 删除指定用户信息
     * @param userId
     */
    @Override
    public void deletAdminMsg(Integer userId) {
        adminDao.deleteById(userId);
    }

    /**
     * 模糊查询admin
     * @param username
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<library_admin> findByUserName(String username, Integer page, Integer limit) {
        return adminDao.findByUsername(username, (page-1)*limit, limit);
    }

    /**
     * 查询admin条数
     * @param username
     * @return
     */
    @Override
    public Integer findByUserNameCount(String username) {
        return adminDao.findByUsernameCount(username);
    }

    /**
     * 添加admin用户
     * @param username
     * @param password
     * @param sex
     * @param phone
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void addAdminMsg(String username, String password, String sex, String phone) {
        String pass = DigestUtils.md5Hex(username + password + "homeworklibrary");

        library_admin admin = new library_admin();
        admin.setUserid(null);
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setSex(sex);
        admin.setPhone(phone);
        admin.setPower("admin");

        adminDao.save(admin);

    }

    /**
     * 显示user信息
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<library_user> findAllUser(Integer page, Integer limit) {
        return userDao.findAllUser((page-1)*limit, limit);
    }

    /**
     * 显示user条数
     * @return
     */
    @Override
    public Integer findAllUser() {
        return userDao.findAllUserCount();
    }

    /**
     * 更新用户性别、手机号
     * @param userId
     * @param sex
     * @param phone
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateUserMsg(Integer userId, String sex, String phone) {
        userDao.updateUserMsg(userId, sex, phone);
    }

    /**
     * 更新密码
     * @param userId
     * @param password
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void setUserPassword(Integer userId,String username, String password) {
        String pass = DigestUtils.md5Hex(username + password + "homeworklibrary");
        userDao.setUserPassword(userId, pass);
    }

    /**
     * 删除id的用户
     * @param userId
     */
    @Override
    public void deleteByUserId(Integer userId) {
        userDao.deleteById(userId);
    }

    /**
     * 模糊查询用户
     * @param username
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<library_user> findUserName(String username, Integer page, Integer limit) {
        return userDao.findAllByUserName(username, (page-1)*limit, limit);
    }

    /**
     * 模糊查询用户数
     * @param username
     * @return
     */
    @Override
    public Integer findUserNameCount(String username) {
        return userDao.findAllByUserNameCount(username);
    }

}
