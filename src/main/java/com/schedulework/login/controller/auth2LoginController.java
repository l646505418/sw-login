package com.schedulework.login.controller;


import com.schedulework.login.util.oauth2LoginGithub;
import com.schedulework.login.util.oauth2LoginGoogle;
import com.schedulework.login.vo.backResponse;
import com.schedulework.login.vo.responseEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author:Li Jinming
 * @Description:
 * @date:2023-02-15
 */
@Slf4j
@RestController
public class auth2LoginController {
    @Autowired
    oauth2LoginGoogle oauth2LoginGoogle;
    @Autowired
    oauth2LoginGithub oauth2LoginGithub;
    @PostMapping(path = "/auth2/google")
    backResponse auth2GoogleLogin(HttpServletResponse response) {
        String authUrl= oauth2LoginGoogle.oauth2Login();
        try{
            response.sendRedirect(authUrl);
        } catch (IOException e){
            log.error(String.format("something happend when log in :%s",e.getMessage()));
    return new backResponse(responseEnum.LOGIN_FAILED.getStatusCode(),responseEnum.LOGIN_FAILED.getStatusDescription(),null);
        }
        return new backResponse(responseEnum.LOGIN_PROCESING.getStatusCode(),responseEnum.LOGIN_PROCESING.getStatusDescription(),null);
    }
    @PostMapping(path = "/auth2/github")
    backResponse auth2GithubLogin(HttpServletResponse response) {
        String authUrl= oauth2LoginGithub.oauth2Login();
        try{
            response.sendRedirect(authUrl);
        } catch (IOException e){
            log.error(String.format("something happend when log in :%s",e.getMessage()));
            return new backResponse(responseEnum.LOGIN_FAILED.getStatusCode(),responseEnum.LOGIN_FAILED.getStatusDescription(),null);
        }
        return new backResponse(responseEnum.LOGIN_PROCESING.getStatusCode(),responseEnum.LOGIN_PROCESING.getStatusDescription(),null);
    }
}
