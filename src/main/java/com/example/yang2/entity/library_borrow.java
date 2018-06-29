package com.example.yang2.entity;

import javax.persistence.*;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.entity
 * @Description: 用户借阅数据库持久层POJO
 * @Date: Created in 19:05 2018/6/6
 */
@Entity
@Table(name = "library_borrow")
public class library_borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20)
    private Integer bookid;
    @Column(length = 20)
    private String username;
    @Column(length = 20)
    private String bookname;
    @Column(length = 50)
    private String loan;
    @Column(length = 50)
    private String back;
    @Column(length = 5, nullable = false, columnDefinition = "varchar default '是'")
    private String isrenew;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getIsrenew() {
        return isrenew;
    }

    public void setIsrenew(String isrenew) {
        this.isrenew = isrenew;
    }
}
