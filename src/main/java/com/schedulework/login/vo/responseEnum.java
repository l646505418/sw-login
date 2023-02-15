package com.schedulework.login.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author:Li Jinming
 * @Description:
 * @date:2023-02-13
 */
@Getter
@ToString
@AllArgsConstructor

public enum responseEnum {
    LOGIN_SUCCESS(2001,"login success"),
    LOGON_SUCCESS(2002,"logon success"),
    LOGON_FAILED(3001,"user already exist"),
    USER_NOT_FOUND(4001,"user not found "),
    BAD_REQUEST(4002,"userInfo is null"),
    EDIT_FAILED(3002,"userInfo is null"),
    EDIT_SUCCESS(2003,"userInfo is null");
//    LOGIN_ERROR (403 , "username authorised fail,format is not right"),
//    USERNAME_EMPTY(403, "username is empty"),
//    TOKEN_VERIFY_FAILED(4001,"token verify failed"),
//    TOKEN_EXPIRED(4002,"token has expired");


    private int statusCode;
    private String statusDescription;
}
