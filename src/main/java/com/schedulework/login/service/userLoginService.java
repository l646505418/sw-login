package com.schedulework.login.service;

import com.schedulework.login.vo.backResponse;

public interface userLoginService {
    public abstract backResponse doLogin(String username, String password);

    public abstract backResponse doLogon(String username,String password);
}
