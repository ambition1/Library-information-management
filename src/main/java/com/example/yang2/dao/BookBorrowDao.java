package com.example.yang2.dao;

import com.example.yang2.entity.library_borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.dao
 * @Description: 用户借书数据库操作接口
 * @Date: Created in 19:04 2018/6/6
 */

public interface BookBorrowDao extends JpaRepository<library_borrow, Integer> {

    /**
     * 查询某个用户的借阅信息
     * @param username
     * @param pageNum
     * @param pageLimit
     * @return
     */
    @Query(value = "select * from library_borrow where username=?1 limit ?2, ?3", nativeQuery = true)
    List<library_borrow> findUserBorrow(String username, Integer pageNum, Integer pageLimit);

    /**
     * 返回指定用户的借阅条数
     * @param username
     * @return
     */
    @Query(value = "select count(*) from library_borrow where username=?1", nativeQuery = true)
    Integer findUserBorrowCount(String username);

    /**
     * 返回所有借阅信息
     * @param pageNum
     * @param pagLimit
     * @return
     */
    @Query(value = "select * from library_borrow limit ?1, ?2", nativeQuery = true)
    List<library_borrow> findAllBorrow(Integer pageNum, Integer pagLimit);

    /**
     * 返回所有借阅信息的条数
     * @return
     */
    @Query(value = "select count(*) from library_borrow", nativeQuery = true)
    Integer findBorrowCount();

    /**
     * 修改归还时间
     * @param id
     * @param backTime
     *
     * 在自定义执行updata/delete操作时必须使用@Modifying注解，声明读写操作
     * 出现Executing an update/delete query的原因是没有事务
     * 单独测试时直接在@Modifying下加入@Transactional即可
     * 正常业务时  需要在方法上声明@Transactional完成事物
     */
    @Modifying
    @Query(value = "update library_borrow borrow set borrow.back=?2, borrow.isrenew='否' where borrow.id=?1")
    void setBackTimeById(Integer id, String backTime);

    /**
     * 获取用户是否可以续借
     * @param id
     * @return
     */
    @Query(value = "select isrenew from library_borrow where id=?", nativeQuery = true)
    String getUserIsRenew(Integer id);

}
