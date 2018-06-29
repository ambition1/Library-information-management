package com.example.yang2.service.AdminOperation.impl;

import com.example.yang2.dao.BookBorrowDao;
import com.example.yang2.dao.BookDao;
import com.example.yang2.entity.library_book;
import com.example.yang2.entity.library_borrow;
import com.example.yang2.service.AdminOperation.AdminOperationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.service.AdminOperation.impl
 * @Description:
 * @Date: Created in 18:33 2018/6/8
 */

@Service
public class AdminOperationImpl implements AdminOperationService {

    @Resource
    private BookDao bookDao;
    @Resource
    private BookBorrowDao bookBorrowDao;

    /**
     * 更新图书信息
     * @param bookId
     * @param bookName
     * @param bookAuthor
     * @param bookPublisher
     * @param bookPrice
     * @param bookCategory
     * @param booksurplus
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public void adminUpdata(Integer bookId, String bookName, String bookAuthor, String bookPublisher, Double bookPrice, String bookCategory, Integer booksurplus) {

        library_book book = new library_book();
        book.setBookid(bookId);
        book.setBookname(bookName);
        book.setBookauthor(bookAuthor);
        book.setBookpublisher(bookPublisher);
        book.setBookprice(bookPrice);
        book.setBookcategory(bookCategory);
        book.setBooksurplus(booksurplus);

        bookDao.save(book);
    }

    /**
     * 删除图书
     * @param bookId
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public void adminDelete(Integer bookId){
        bookDao.deleteById(bookId);
    }

    /**
     * 归还图书操作
     * @param id 每条借阅数据中唯一的标识符
     * @param bookId 书本信息中唯一标识
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public void adminBorrow(Integer id, Integer bookId) {
        bookBorrowDao.deleteById(id);

        bookDao.addByBookIdToBooksurplus(bookId);
    }

    /**
     * 借阅图书信息
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<library_borrow> userBorrowList(Integer page, Integer limit) {
        return bookBorrowDao.findAllBorrow((page-1)*limit, limit);
    }

    /**
     * 借阅总条数
     * @return
     */
    @Override
    public Integer userBorrowCount() {
        return bookBorrowDao.findBorrowCount();
    }

    /**
     * 查询某用户借阅信息
     * @param userName
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<library_borrow> userBorrowListByUserName(String userName, Integer page, Integer limit) {
        return bookBorrowDao.findUserBorrow(userName, page, limit);
    }

    /**
     * 某用户借阅条数
     * @param userName
     * @return
     */
    @Override
    public Integer userBorrowByUserNameCount(String userName) {
        return bookBorrowDao.findUserBorrowCount(userName);
    }

    /**
     * 添加新图书
     * @param bookname
     * @param bookauthor
     * @param bookpublisher
     * @param bookprice
     * @param bookcategory
     * @param booksurplus
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void addBookMsg(String bookname, String bookauthor, String bookpublisher, Double bookprice, String bookcategory, Integer booksurplus) {
        library_book book = new library_book();
        book.setBookid(null);
        book.setBookname(bookname);
        book.setBookauthor(bookauthor);
        book.setBookpublisher(bookpublisher);
        book.setBookprice(bookprice);
        book.setBookcategory(bookcategory);
        book.setBooksurplus(booksurplus);

        bookDao.save(book);
    }
}
