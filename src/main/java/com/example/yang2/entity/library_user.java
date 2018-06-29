package com.example.yang2.entity;

import javax.persistence.*;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.entity
 * @Description: 用户实体类
 * @Date: Created in 10:32 2018/6/4
 */

@Entity
@Table(name = "library_user")
public class library_user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
    @Column(length = 20)
    private String username;
    @Column(length = 50)
    private String password;
    @Column(length = 10, nullable = false, columnDefinition = "varchar default '男'")
    private String sex;
    @Column(length = 11)
    private String phone;
    @Column(length = 10, nullable = false, columnDefinition = "varchar default 'general'")
    private String power;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
