package com.schedulework.login.controller;


import com.schedulework.login.Entity.userLoginInfo;
import com.schedulework.login.service.userLoginService;
import com.schedulework.login.vo.backResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author:Li Jinming
 * @Description:
 * @date:2023-02-13
 */

@RestController
public class loginController {
@Autowired
userLoginService userLoginService;

    @PostMapping(path = "/login")
    backResponse doLogin(@RequestParam String username, @RequestParam String password){
        return userLoginService.doLogin(username,password);
    }
    @PostMapping(path = "/logon")
    backResponse doLogon(@RequestParam String username, @RequestParam String password){
        return userLoginService.doLogon(username,password);
    }


    @PostMapping(path = "/addProtofile")
    backResponse doEditInfo(@RequestBody userLoginInfo newUserInfo){
        //TODO:add json validate logic
        return null;

    }

}
