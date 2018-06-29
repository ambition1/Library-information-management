package com.example.yang2.controller;

import com.example.yang2.entity.StatusCode;
import com.example.yang2.service.AdminOperation.AdminOperationService;
import com.example.yang2.service.Page.PagingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.controller
 * @Description: 管理员操作controller
 * @Date: Created in 10:46 2018/6/8
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminOperation {

    @Resource
    private AdminOperationService adminOperationService;
    @Resource
    private PagingService pagingService;

    @RequestMapping(value = "/list")
    public ModelAndView admin(){
        return new ModelAndView("admin");
    }

    @RequestMapping(value = "/userborrow")
    public ModelAndView adminBorrow(){
        return new ModelAndView("adminborrow");
    }

    /**
     * 更新图书信息操作
     * @param bookId
     * @param bookName
     * @param bookAuthor
     * @param bookPublisher
     * @param bookPrice
     * @param bookCategory
     * @param bookSurplus
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public StatusCode updataBookMsg(@RequestParam(value = "bookid") Integer bookId, @RequestParam(value = "bookname") String bookName,
                                    @RequestParam(value = "bookauthor") String bookAuthor, @RequestParam(value = "bookpublisher") String bookPublisher,
                                    @RequestParam(value = "bookprice") String bookPrice, @RequestParam(value = "bookcategory") String bookCategory,
                                    @RequestParam(value = "booksurplus") String bookSurplus){
        StatusCode code = new StatusCode();
        try {
            Double price = Double.parseDouble(bookPrice);
            Integer surplus = Integer.parseInt(bookSurplus);
            try {
                adminOperationService.adminUpdata(bookId, bookName, bookAuthor, bookPublisher, price, bookCategory, surplus);
                code.setStatuscode("700000");
                code.setStatus("更新成功！");
                return code;
            }catch (Exception e){
                code.setStatuscode("700001");
                code.setStatus("更新失败！");
                return code;
            }
        }catch (Exception e){
            code.setStatuscode("700002");
            code.setStatus("修改的信息中，价格和剩余数量只能为数字！");
            return code;
        }
    }

    /**
     * 删除图书信息
     * @param bookId
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String deleteBook(@RequestParam(value = "bookid") Integer bookId){

        try {
            adminOperationService.adminDelete(bookId);
            return "删除成功！";
        }catch (Exception e){
            return "删除失败！";
        }
    }

    /**
     * 归还图书操作
     * @param id
     * @param bookId
     * @return
     */
    @RequestMapping(value = "/back")
    @ResponseBody
    public StatusCode userBackBook(@RequestParam(value = "id") Integer id, @RequestParam(value = "bookid") Integer bookId){
        StatusCode code = new StatusCode();
        try {
            adminOperationService.adminBorrow(id, bookId);
            code.setStatuscode("900000");
            code.setStatus("归还成功！");
            return code;
        }catch (Exception e){
            code.setStatuscode("900001");
            code.setStatus("归还失败！");
            return code;
        }
    }

    /**
     * 显示所有用户借阅信息
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/borrowlist")
    @ResponseBody
    public Map<String, Object> userBorrowList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                              @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        Map<String, Object> bookBorrow = new HashMap<>();
        bookBorrow.put("code", 0);
        bookBorrow.put("msg", "");
        bookBorrow.put("count", adminOperationService.userBorrowCount());
        bookBorrow.put("data", adminOperationService.userBorrowList(page, limit));

        return bookBorrow;
    }

    /**
     * 搜索指定用户信息
     * @param userName
     * @param pageNum
     * @param pageLimit
     * @return
     */
    @RequestMapping(value = "/searchuserborrow")
    @ResponseBody
    public Map<String, Object> userBorrowByUserName(@RequestParam(value = "username") @NotNull String userName,
                                                    @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "limit", defaultValue = "10") Integer pageLimit){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pagingService.userBorrowList(userName));
        map.put("data", pagingService.userBorrowList(userName, pageNum, pageLimit));
        return map;
    }

    /**
     * 添加图书信息
     * @param bookname
     * @param bookauthor
     * @param bookpublisher
     * @param bookprice
     * @param bookcategory
     * @param booksurplus
     * @return
     */
    @RequestMapping(value = "/addbook")
    @ResponseBody
    public StatusCode addBookMsg(@RequestParam(value = "bookname") String bookname, @RequestParam(value = "bookauthor")String bookauthor,
                                 @RequestParam(value = "bookpublisher")String bookpublisher,@RequestParam(value = "bookprice") Double bookprice,
                                 @RequestParam(value = "bookcategory")String bookcategory, @RequestParam(value = "booksurplus")Integer booksurplus){
        StatusCode code = new StatusCode();

        try {
            adminOperationService.addBookMsg(bookname, bookauthor, bookpublisher, bookprice, bookcategory, booksurplus);
            code.setStatuscode("800000");
            code.setStatus("添加成功！");
            return code;
        } catch (Exception e) {
            code.setStatuscode("800001");
            code.setStatus("添加失败！");
            return code;
        }

    }

}
