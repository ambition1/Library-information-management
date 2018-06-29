package com.example.yang2.service.Page;

import com.example.yang2.entity.library_book;
import com.example.yang2.entity.library_borrow;

import java.util.List;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.service.Page
 * @Description: 分页接口
 * @Date: Created in 13:57 2018/6/5
 */
public interface PagingService {

    /**
     * 分割页数抽象方法
     *
     * @param pageNum   需要获取的页数
     * @param pageLimit 每次分页中每页的数据长度
     * @return list对象集合
     */
    List<library_book> subPage(Integer pageNum, Integer pageLimit);

    /**
     * 检索所有的图书条数
     *
     * @return 条数
     */
    Integer bookNumber();

    /**
     * 用户查询图书，支持模糊搜索图书名
     *
     * @param bookName
     * @param pageNum
     * @param pageLimit
     * @return
     */
    List<library_book> userSearchBook(String bookName, Integer pageNum, Integer pageLimit);

    /**
     * 用户查询图书的总条数
     *
     * @param bookName
     * @return
     */
    Integer userSearchBook(String bookName);

    /**
     * 用户借阅图书信息查询
     * @param username
     * @param pageNum
     * @param pageLimit
     * @return
     */
    List<library_borrow> userBorrowList(String username, Integer pageNum, Integer pageLimit);

    /**
     * 用户借阅图书数目
     * @param username
     * @return
     */
    Integer userBorrowList(String username);
}
