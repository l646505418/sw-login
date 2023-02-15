package com.schedulework.login.util;


import lombok.Data;

/**
 * @author:Li Jinming
 * @Description:
 * @date:2022-12-31
 */

@Data
public abstract class endPoint {
    private String redirectUrl;

    public endPoint(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
