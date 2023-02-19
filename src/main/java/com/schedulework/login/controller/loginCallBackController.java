package com.schedulework.login.controller;


import com.schedulework.login.Entity.userLoginInfo;
import com.schedulework.login.mapper.userloginMapper;
import com.schedulework.login.util.JWTUtil;
import com.schedulework.login.util.oauth2LoginGithub;
import com.schedulework.login.util.oauth2LoginGoogle;
import com.schedulework.login.vo.backResponse;
import com.schedulework.login.vo.responseEnum;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author:Li Jinming
 * @Description:
 * @date:2023-02-15
 */
@Slf4j
@RestController
public class loginCallBackController {
    @Autowired
 oauth2LoginGoogle oauth2LoginGoogle;
    @Autowired
    oauth2LoginGithub oauth2LoginGithub;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    userloginMapper userloginMapper;

    @RequestMapping(value = "google/callback",method = RequestMethod.GET)
    public backResponse redirectGoogleApi(@RequestParam String code, @RequestParam String scope, HttpServletResponse response) throws IOException {
        log.info(code+scope);
        log.info("auth successfully,start get token");
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
//        httpHeaders.add("Content-Type","application/x-www-form-urlencoded");
        MultiValueMap<String,String> paramMap=new LinkedMultiValueMap<>();
        paramMap.add("client_id", oauth2LoginGoogle.getClientId());
        paramMap.add("grant_type","authorization_code");
        paramMap.add("code",code);
        HttpEntity<MultiValueMap<String,String>> httpEntity=new HttpEntity<>(paramMap,httpHeaders);
        ResponseEntity<String> responseEntity;
        try {
            responseEntity= restTemplate.postForEntity(oauth2LoginGoogle.getBaseTokenUri(), httpEntity, String.class);
        }catch (Exception e){
            log.warn (String.format("there are some errors happend when get token:%s",e.getMessage()));
            return new backResponse(responseEnum.LOGIN_FAILED.getStatusCode(),responseEnum.LOGIN_FAILED.getStatusDescription(),null);
        }
        log.info("successfully get token ");
        return new backResponse(responseEnum.LOGIN_SUCCESS.getStatusCode(),responseEnum.LOGIN_SUCCESS.getStatusDescription(),(Object) responseEntity.getBody());
    }
    @RequestMapping(value = "github/callback",method = RequestMethod.GET)
    public backResponse redirectGithubApi(@RequestParam String code,  HttpServletResponse response) throws IOException {
        //TODO:add logic when user denied
        log.info(code);
        log.info("auth successfully,start get token");
        HashMap<String,String> tokenInfo=oauth2LoginGithub.getAccessToken(code);
        if(tokenInfo.isEmpty())
            return new backResponse(responseEnum.LOGIN_FAILED.getStatusCode(),responseEnum.LOGIN_FAILED.getStatusDescription(),null);
        log.info("successfully get token,start get userInfo");
        HashMap<String,String> userInfo =oauth2LoginGithub.getUserInfo(tokenInfo.get("access_token"));
        log.info("successfully get userInfo, generate the jwt token");
        String username=userInfo.get("login");
        String token= JWTUtil.createToken(username);
        response.setHeader(JWTUtil.JWT_HEADER,JWTUtil.JWT_PREFIX+token);
        return oauth2Login(username,token);
    }


    private backResponse oauth2Login(String username,String token){
        userLoginInfo user=userloginMapper.findByName(username);
        if(user==null){
            userLoginInfo addedUser=new userLoginInfo(username,token);
            int userId=userloginMapper.insertUser(addedUser);
           return new backResponse(responseEnum.LOGIN_SUCCESS.getStatusCode(),responseEnum.LOGIN_SUCCESS.getStatusDescription(),new Object[]{ userId,username});
        }
        userLoginInfo updatedUser=new userLoginInfo(username,token);
        userloginMapper.updateUserInfo(updatedUser);
        return new backResponse(responseEnum.LOGIN_SUCCESS.getStatusCode(),responseEnum.LOGIN_SUCCESS.getStatusDescription(),new Object[]{ user.getId(),username});

    }
}
