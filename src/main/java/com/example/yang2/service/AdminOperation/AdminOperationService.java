package com.example.yang2.service.AdminOperation;

import com.example.yang2.entity.library_borrow;

import java.util.List;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.service.AdminOperation
 * @Description: 管理员操作Service
 * @Date: Created in 11:27 2018/6/8
 */
public interface AdminOperationService {

    /**
     * 更新图书信息接口
     * @param bookId
     * @param bookName
     * @param bookAuthor
     * @param bookPublisher
     * @param bookPrice
     * @param bookCategory
     * @param booksurplus
     */
     void adminUpdata(Integer bookId, String bookName, String bookAuthor, String bookPublisher, Double bookPrice,
                      String bookCategory, Integer booksurplus);

    /**
     * 删除图书接口
     * @param bookId
     */
     void adminDelete(Integer bookId);

    /**
     * 用户归还操作
     * @param id 每条借阅数据中唯一的标识符
     * @param bookId 书本信息中唯一标识
     */
     void adminBorrow(Integer id, Integer bookId);

    /**
     * 所有借阅信息列表
     * @param page
     * @param limit
     * @return
     */
     List<library_borrow> userBorrowList(Integer page, Integer limit);

    /**
     * 所有借阅信息总条数
     * @return
     */
     Integer userBorrowCount();

    /**
     * 查询指定用户的借阅信息
     * @param userName
     * @param page
     * @param limit
     * @return
     */
     List<library_borrow> userBorrowListByUserName(String userName, Integer page, Integer limit);

    /**
     * 查询指定用户的借阅条数
     * @param userName
     * @return
     */
     Integer userBorrowByUserNameCount(String userName);

    /**
     * 添加图书信息
     * @param bookname
     * @param bookauthor
     * @param bookpublisher
     * @param bookprice
     * @param bookcategory
     * @param booksurplus
     */
     void addBookMsg(String bookname, String bookauthor, String bookpublisher, Double bookprice, String bookcategory, Integer booksurplus);

}
