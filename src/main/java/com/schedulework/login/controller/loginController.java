package com.schedulework.login.controller;


import com.schedulework.login.Entity.userLoginInfo;
import com.schedulework.login.service.userEditInfoService;
import com.schedulework.login.service.userLoginService;
import com.schedulework.login.util.JWTUtil;
import com.schedulework.login.vo.backResponse;
import com.schedulework.login.vo.responseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author:Li Jinming
 * @Description:
 * @date:2023-02-13
 */
@Slf4j
@RestController
public class loginController {
@Autowired
userLoginService userLoginService;
@Autowired
    userEditInfoService  userEditInfoService;
    @PostMapping(path = "/login")
    backResponse doLogin(@RequestParam String username, @RequestParam String password, HttpServletResponse response){
         backResponse  loginResponse=userLoginService.doLogin(username,password);
         if (loginResponse.getCode()==responseEnum.LOGIN_SUCCESS.getStatusCode()){
             userLoginInfo user=(userLoginInfo) loginResponse.getObject();
             log.info(String.format("for user %s,,start create JWT token"),user.getId());
             String token= JWTUtil.createToken(username);
             response.setHeader(JWTUtil.JWT_HEADER,JWTUtil.JWT_PREFIX+token);
         }
        return loginResponse;
    }
    @PostMapping(path = "/logon")
    backResponse doLogon(@RequestParam String username, @RequestParam String password,HttpServletResponse response){
        backResponse  logonResponse=userLoginService.doLogon(username,password);
        if (logonResponse.getCode()==responseEnum.LOGON_SUCCESS.getStatusCode()){
            float userId=(float) logonResponse.getObject();
            log.info(String.format("for user %s,,start create JWT token"),userId);
            String token= JWTUtil.createToken(username);
            response.setHeader(JWTUtil.JWT_HEADER,JWTUtil.JWT_PREFIX+token);
        }
        return logonResponse;
    }


    @PostMapping(path = "/addProtofile")
    backResponse doEditInfo(@RequestBody userLoginInfo newUserInfo){
        //TODO:add json validate logic
        return userEditInfoService.editProtofile(newUserInfo);

    }

}
