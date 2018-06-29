package com.example.yang2.entity;

import javax.persistence.*;

/**
 * @Author: yang
 * @ProjectName: yang
 * @Package: com.example.yang.entity
 * @Description: 图书信息数据库持久层POJO
 * @Date: Created in 10:06 2018/6/3
 */
//注解声明该类是一个Hibernate的持久化类
@Entity
@Table(name = "library_book")
public class library_book {

    /**
     * 标记主键，并设置自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20)
    private Integer bookid;
    @Column(length = 50)
    private String bookname;
    @Column(length = 50)
    private String bookauthor;
    @Column(length = 50)
    private String bookpublisher;
    @Column(length = 20)
    private Double bookprice;
    @Column(length = 20)
    private String bookcategory;
    @Column(length = 50)
    private Integer booksurplus;


    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookauthor() {
        return bookauthor;
    }

    public void setBookauthor(String bookauthor) {
        this.bookauthor = bookauthor;
    }

    public String getBookpublisher() {
        return bookpublisher;
    }

    public void setBookpublisher(String bookpublisher) {
        this.bookpublisher = bookpublisher;
    }

    public Double getBookprice() {
        return bookprice;
    }

    public void setBookprice(Double bookprice) {
        this.bookprice = bookprice;
    }

    public String getBookcategory() {
        return bookcategory;
    }

    public void setBookcategory(String bookcategory) {
        this.bookcategory = bookcategory;
    }

    public Integer getBooksurplus() {
        return booksurplus;
    }

    public void setBooksurplus(Integer booksurplus) {
        this.booksurplus = booksurplus;
    }
}
