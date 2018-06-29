package com.example.yang2.service.SuperAdminOperation;

import com.example.yang2.entity.library_admin;
import com.example.yang2.entity.library_user;

import java.util.List;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.service.SuperAdminOperation
 * @Description: root管理员操作接口
 * @Date: Created in 20:57 2018/6/9
 */
public interface RootOperationService {

    /**
     * 显示所有admin用户信息
     * @param pageNum
     * @param pageLimit
     * @return
     */
    List<library_admin> findAllAdminMsg(Integer pageNum, Integer pageLimit);

    /**
     * 显示admin用户总数
     * @return
     */
    Integer findAllAdminMsgCount();

    /**
     * 更新admin性别和手机号
     * @param userId
     * @param sex
     * @param phone
     */
    void updateAdminMsg(Integer userId, String sex, String phone);

    /**
     * 重置admin密码
     * @param userId
     * @param userName
     * @param passWord
     */
    void setAdminPassWord(Integer userId,String userName, String passWord);

    /**
     * 删除指定id的admin用户信息
     * @param userId
     */
    void deletAdminMsg(Integer userId);

    /**
     * 模糊搜索指定admin
     * @param username
     * @param page
     * @param limit
     * @return
     */
    List<library_admin> findByUserName(String username, Integer page, Integer limit);

    /**
     * 搜索用户条数
     * @param username
     * @return
     */
    Integer findByUserNameCount(String username);

    /**
     * 添加admin用户
     * @param username
     * @param password
     * @param sex
     * @param phone
     */
    void addAdminMsg(String username, String password, String sex, String phone);

    /**
     * 展示所有user用户
     * @param page
     * @param limit
     * @return
     */
    List<library_user> findAllUser(Integer page, Integer limit);

    /**
     * 展示所有用户条数
     * @return
     */
    Integer findAllUser();

    /**
     * 更新性别、手机号
     * @param userId
     * @param sex
     * @param phone
     */
    void updateUserMsg(Integer userId, String sex, String phone);

    /**
     * 更新密码
     * @param userId
     * @param username
     * @param password
     */
    void setUserPassword(Integer userId,String username, String password);

    /**
     * 删除Id的用户
     * @param userId
     */
    void deleteByUserId(Integer userId);

    /**
     * 模糊查询用户
     * @param username
     * @return
     */
    List<library_user> findUserName(String username, Integer page, Integer limit);

    /**
     * 返回模糊查询用户数
     * @param username
     * @return
     */
    Integer findUserNameCount(String username);
}
