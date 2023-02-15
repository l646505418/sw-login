package com.schedulework.login.controller;


import com.schedulework.login.vo.backResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:Li Jinming
 * @Description:
 * @date:2023-02-15
 */

@RestController
public class auth2LoginController {
    @PostMapping(path = "/auth2/google")
    backResponse auth2GoogleLogin(@RequestParam String username, @RequestParam String password){
        return null;
    }
}
