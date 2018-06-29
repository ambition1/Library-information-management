package com.example.yang2.dao;

import com.example.yang2.entity.library_admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.dao
 * @Description: 管理员信息库持久层
 * @Date: Created in 20:54 2018/6/9
 */
public interface AdminDao extends JpaRepository<library_admin, Integer> {

    /**
     * 查询admin登录密码
     * @param username
     * @return
     */
    @Query(value = "select password from library_admin where username=?1 or phone=?1", nativeQuery = true)
    String adminLogin(String username);

    /**
     * 查询所有管理员信息
     * @param pageNum
     * @param pageLimit
     * @return
     */
    @Query(value = "select * from library_admin limit ?1, ?2", nativeQuery = true)
    List<library_admin> findAllAdminMsg(Integer pageNum, Integer pageLimit);

    /**
     * 查询管理员信息总条数
     * @return
     */
    @Query(value = "select count(*) from library_admin ", nativeQuery = true)
    Integer findAllAdminMsgCount();

    /**
     * 查询指定admin
     * @param username
     * @return
     */
    @Query(value = "select * from library_admin where username like %?1% limit ?2, ?3", nativeQuery = true)
    List<library_admin> findByUsername(String username, Integer page, Integer limit);

    /**
     * 查询admin的条数
     * @param username
     * @return
     */
    @Query(value = "select count(*) from library_admin where username like %?1%", nativeQuery = true)
    Integer findByUsernameCount(String username);

    /**
     * 更新除用户名、密码和id以外的信息
     * @param userid
     * @param sex
     * @param phone
     */
    @Modifying
    @Query(value = "update library_admin set sex = ?2, phone = ?3 where userid = ?1", nativeQuery = true)
    void updateByUserId(Integer userid, String sex, String phone);

    /**
     * 更新密码
     * @param userId
     * @param passWord
     */
    @Modifying
    @Query(value = "update library_admin set password = ?2 where userid = ?1", nativeQuery = true)
    void setAdminPassWord(Integer userId, String passWord);

    /**
     * 查询权限
     * @param username
     * @return
     */
    @Query(value = "select power from library_admin where username=?1 or phone=?1", nativeQuery = true)
    String findPower(String username);

}
