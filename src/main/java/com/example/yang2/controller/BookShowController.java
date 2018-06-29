package com.example.yang2.controller;

import com.example.yang2.service.Page.PagingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.controller
 * @Description: 向用户展示所有的图书信息
 * @Date: Created in 10:41 2018/6/5
 */
@Controller
@RequestMapping(value = "/client")
public class BookShowController {

    @Resource
    private PagingService pagingService;

    @RequestMapping(value = "/show")
    public ModelAndView show() {
        return new ModelAndView("show");
    }

    @RequestMapping(value = "/showborrow")
    public ModelAndView showBorrow(){
        return new ModelAndView("showborrow");
    }

    /**
     * 返回所有图书信息
     *
     * @param page  当前页码
     * @param limit 每页最大条数
     * @return 返回map对象 由Spring自动序列化为json，进行页面局部刷新
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> bookList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        Map<String, Object> book = new HashMap<>();
        book.put("code", 0);
        book.put("msg", "");
        book.put("count", pagingService.bookNumber());
        book.put("data", pagingService.subPage(page, limit));
        return book;
    }

    /**
     * 用于返回用户搜索图书指定图书
     * 支持模糊搜索书名
     *
     * @param bookname
     * @param page
     * @param limit
     * @return map集合
     */
    @RequestMapping(value = "/search")
    @ResponseBody
    public Map<String, Object> searchList(@RequestParam(value = "bookname", defaultValue = "") String bookname,
                                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "limit", defaultValue = "10") Integer limit) {

        Map<String, Object> search = new HashMap<>();
        search.put("code", 0);
        search.put("msg", "");
        search.put("count", pagingService.userSearchBook(bookname));
        search.put("data", pagingService.userSearchBook(bookname, page, limit));
        return search;
    }

    /**
     * 向用户展示图书借阅信息
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(value = "/borrowlist")
    @ResponseBody
    public Map<String, Object> userBorrowList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                              @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                              HttpServletRequest request){

        String username = (String)request.getSession().getAttribute("username");
        Map<String, Object> borrow = new HashMap<>();
        borrow.put("code", 0);
        borrow.put("msg", "");
        borrow.put("count", pagingService.userBorrowList(username));
        borrow.put("data", pagingService.userBorrowList(username, page, limit));
        return borrow;
    }

}
