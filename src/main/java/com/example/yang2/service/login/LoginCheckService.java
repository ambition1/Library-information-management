package com.example.yang2.service.login;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.service.login
 * @Description: 登录检验接口
 * @Date: Created in 22:32 2018/6/3
 */


public interface LoginCheckService {

    /**
     * 判断用户是否可以登录
     *
     * @param username
     * @param password
     * @return
     */
    Boolean checkUserLogin(String username, String password);

    /**
     * 判断admin是否可以登录
     * @param username
     * @param password
     * @return
     */
    Boolean checkAdminLogin(String username, String password);

    /**
     * 查询admin用户权限
     * @param username
     * @return
     */
    String findAdminPower(String username);

}
