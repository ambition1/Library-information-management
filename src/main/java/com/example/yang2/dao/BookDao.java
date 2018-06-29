package com.example.yang2.dao;

import com.example.yang2.entity.library_book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: yang
 * @ProjectName: yang
 * @Package: com.example.yang.dao
 * @Description: Book数据库操作层
 * @Date: Created in 11:00 2018/6/3
 */

/**
 * 继承JPA数据库操作类
 * JpaRepository<T, ID>
 * T: 指向要操作的数据库的实体类（持久层类）
 * ID：该类值的主键的类型
 */

public interface BookDao extends JpaRepository<library_book, Integer> {

    /**
     * 获取分页内容
     *
     * @return 该页面下的图书列表
     */
    @Query(value = "select * from library_book limit ?1, ?2", nativeQuery = true)
    List<library_book> findPagination(Integer pageNamber, Integer pageLimit);

    /**
     * 获取所有图书条数
     *
     * @return
     */
    @Query(value = "select count(*) from library_book ", nativeQuery = true)
    Integer findAllBookNumber();

    /**
     * 用户搜索图书，支持图书名字模糊搜素，并执行分页
     *
     * @param bookName
     * @param pageNum
     * @param pageLimit
     * @return list对象
     */
    @Query(value = "select * from library_book where bookname like %?1% limit ?2, ?3", nativeQuery = true)
    List<library_book> findUserSearch(String bookName, Integer pageNum, Integer pageLimit);

    /**
     * 返回用户查询图书的条数
     *
     * @param bookName
     * @return Int值
     */
    @Query(value = "select count(*) from library_book where bookname like %?1%", nativeQuery = true)
    Integer findUserSearch(String bookName);

    /**
     * 获取bookid对应图书的剩余数量
     *
     * @param bookId
     * @return
     */
    @Query(value = "select booksurplus from library_book where bookid=?1", nativeQuery = true)
    Integer getBookSurplus(Integer bookId);

    /**
     * 编号id的图书剩余数量减一
     *
     * @param bookId
     */
    @Modifying
    @Query(value = "update library_book set booksurplus=?1 where bookid=?2", nativeQuery = true)
    void deleteByBookIdToBooksurplus(Integer bookSurplus, Integer bookId);

    /**
     * 编号bookID的图书剩余量加一
     * @param bookId
     */
    @Modifying
    @Query(value = "update library_book set booksurplus=booksurplus+1 where bookid=?1", nativeQuery = true)
    void addByBookIdToBooksurplus(Integer bookId);

}
