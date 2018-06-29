package com.example.yang2.dao;

import com.example.yang2.entity.library_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.dao
 * @Description: 用户信息查询dao
 * @Date: Created in 10:59 2018/6/4
 */
public interface UserDao extends JpaRepository<library_user, Integer> {

    /**
     * 查询登录用户密码
     * @param username
     * @return
     */
    @Query(value = "select password from library_user where username=?1 or phone=?1", nativeQuery = true)
    String checkuserlogin(String username);

    /**
     * 查询所有User用户
     * @param page
     * @param limit
     * @return
     */
    @Query(value = "select * from library_user limit ?1, ?2", nativeQuery = true)
    List<library_user> findAllUser(Integer page, Integer limit);

    /**
     * 查询所有User用户的条数
     * @return
     */
    @Query(value = "select count(*) from library_user ", nativeQuery = true)
    Integer findAllUserCount();

    /**
     * 模糊查询用户
     * @param username
     * @param page
     * @param limit
     * @return
     */
    @Query(value = "select * from library_user where username like %?1% limit ?2,?3", nativeQuery = true)
    List<library_user> findAllByUserName(String username, Integer page, Integer limit);

    /**
     * 模糊查询用户的条数
     * @param username
     * @return
     */
    @Query(value = "select count(*) from library_user where username like %?1%", nativeQuery = true)
    Integer findAllByUserNameCount(String username);

    /**
     * 更新User用户的性别，手机号
     * @param userId
     * @param sex
     * @param phone
     */
    @Modifying
    @Query(value = "update library_user set sex = ?2, phone = ?3 where userid = ?1", nativeQuery = true)
    void updateUserMsg(Integer userId, String sex, String phone);

    /**
     * 更新user密码
     * @param userId
     * @param password
     */
    @Modifying
    @Query(value = "update library_user set password = ?2 where userid = ?1", nativeQuery = true)
    void setUserPassword(Integer userId, String password);

    /**
     * 查询某指定用户
     * @param username
     * @return
     */
    List<library_user> findByUsername(String username);

    /**
     * 查询手机号
     * @param phone
     * @return
     */
    List<library_user> findByPhone(String phone);

}
