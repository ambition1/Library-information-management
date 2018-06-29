package com.example.yang2.service.Page.impl;

import com.example.yang2.dao.BookBorrowDao;
import com.example.yang2.dao.BookDao;
import com.example.yang2.entity.library_book;
import com.example.yang2.entity.library_borrow;
import com.example.yang2.service.Page.PagingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.service.Page.impl
 * @Description: 分页实现类
 * @Date: Created in 14:49 2018/6/5
 */
@Service
public class PagingImpl implements PagingService {

    @Resource
    private BookDao bookDao;
    @Resource
    private BookBorrowDao borrowDao;

    /**
     * 实现分页接口操作
     *
     * @param pageNum   需要获取的页数
     * @param pageLimit 每次分页中每页的数据长度
     * @return list对象
     */
    @Override
    public List<library_book> subPage(Integer pageNum, Integer pageLimit) {

        return bookDao.findPagination((pageNum - 1) * pageLimit, pageLimit);
    }

    /**
     * 查询所有图书条数
     *
     * @return
     */
    @Override
    public Integer bookNumber() {
        return bookDao.findAllBookNumber();
    }

    /**
     * 实现用户查询图书（仅支持查询图书名但支持模糊搜素）接口
     *
     * @param bookName
     * @param pageNum
     * @param pageLimit
     * @return
     */
    @Override
    public List<library_book> userSearchBook(String bookName, Integer pageNum, Integer pageLimit) {
        return bookDao.findUserSearch(bookName, (pageNum - 1) * pageLimit, pageLimit);
    }

    /**
     * 用户搜索图书条数
     * @param bookName
     * @return
     */
    @Override
    public Integer userSearchBook(String bookName) {
        return bookDao.findUserSearch(bookName);
    }

    /**
     * 用户借阅信息
     * @param username
     * @param pageNum
     * @param pageLimit
     * @return
     */
    @Override
    public List<library_borrow> userBorrowList(String username, Integer pageNum, Integer pageLimit) {
        return borrowDao.findUserBorrow(username, (pageNum-1)*pageLimit, pageLimit);
    }

    /**
     * 用户借阅图书总条数
     * @param username
     * @return
     */
    @Override
    public Integer userBorrowList(String username) {
        return borrowDao.findUserBorrowCount(username);
    }


}
