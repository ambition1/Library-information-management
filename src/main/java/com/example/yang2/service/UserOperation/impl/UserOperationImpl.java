package com.example.yang2.service.UserOperation.impl;

import com.example.yang2.dao.BookBorrowDao;
import com.example.yang2.dao.BookDao;
import com.example.yang2.entity.library_borrow;
import com.example.yang2.service.UserOperation.TimeOperation.TimeOperationService;
import com.example.yang2.service.UserOperation.UserOperationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.service.UserOperation.impl
 * @Description:
 * @Date: Created in 16:17 2018/6/6
 */
@Service
public class UserOperationImpl implements UserOperationService {

    @Resource
    private BookDao bookDao;
    @Resource
    private BookBorrowDao bookBorrowDao;
    @Resource
    private TimeOperationService timeOperationService;

    /**
     * 注解@Transactional：当方法中的方法抛出异常时，自动回滚数据，数据库中的数据也会被回滚
     *
     * @param bookId
     * @param userName
     * @param bookName
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void userBorrow(Integer bookId, String userName, String bookName) {
        String nowTime = timeOperationService.getNowTime();
        String backTime = timeOperationService.getBackTime();

        Integer bookSurplus = bookDao.getBookSurplus(bookId) - 1;

        bookDao.deleteByBookIdToBooksurplus(bookSurplus, bookId);

        library_borrow borrow = new library_borrow();
        borrow.setId(null);
        borrow.setBookid(bookId);
        borrow.setBookname(bookName);
        borrow.setUsername(userName);
        borrow.setLoan(nowTime);
        borrow.setBack(backTime);
        borrow.setIsrenew("是");
        bookBorrowDao.save(borrow);
    }

    /**
     * 用户续借操作
     * @param id
     * @param backTime
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void userRenew(Integer id, String backTime) {

        String setTime = timeOperationService.setBackTime(backTime);
        bookBorrowDao.setBackTimeById(id, setTime);

    }

    /**
     * 判断用户是否可以续借
     * @param id
     * @return
     */
    @Override
    public boolean userIsRenew(Integer id) {
        if ("是".equals(bookBorrowDao.getUserIsRenew(id))){
            return true;
        }else {
            return false;
        }
    }
}
