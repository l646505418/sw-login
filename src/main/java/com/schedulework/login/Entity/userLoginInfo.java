package com.schedulework.login.Entity;


import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;



/**
 * @author:Li Jinming
 * @Description:user login info
 * @date:2023-02-13
 */

@Data
public class userLoginInfo {

    private  Long Id;
    private String nickname;
    private String password;
    private String headImage;
    private Date lastLoginTime;
    private  Date registTime;
    private String salt;

    public int getLoginCount() {
        return loginCount;
    }

    public userLoginInfo(Long id, String nickname, String password, String headImage, Date lastLoginTime, Date registTime, String salt, int loginCount) {
        Id = id;
        this.nickname = nickname;
        this.password = password;
        this.headImage = headImage;
        this.lastLoginTime = lastLoginTime;
        this.registTime = registTime;
        this.loginCount = loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    private int loginCount;

    public userLoginInfo() {
    }

    public userLoginInfo(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public userLoginInfo(Long id, String nickname, String password, String headImage, Date lastLoginTime, Date registTime, String salt) {
        Id = id;
        this.nickname = nickname;
        this.password = password;
        this.headImage = headImage;
        this.lastLoginTime = lastLoginTime;
        this.registTime = registTime;
        this.salt = salt;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }



}
