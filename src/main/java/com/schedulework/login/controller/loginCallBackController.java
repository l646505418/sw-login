package com.schedulework.login.controller;


import com.schedulework.login.util.JWTUtil;
import com.schedulework.login.util.oauth2LoginGithub;
import com.schedulework.login.util.oauth2LoginGoogle;
import com.schedulework.login.vo.backResponse;
import com.schedulework.login.vo.responseEnum;
import jakarta.servlet.http.HttpServletResponse;
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
        log.info(code);
        log.info("auth successfully,start get token");
        HttpHeaders httpHeaders=new HttpHeaders();
//        httpHeaders.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
////        httpHeaders.add("Content-Type","application/x-www-form-urlencoded");
//        MultiValueMap<String,String> paramMap=new LinkedMultiValueMap<>();
//        paramMap.add("client_id", oauth2LoginGithub.getClientId());
//        paramMap.add("code",code);
        HttpEntity<MultiValueMap<String,String>> httpEntity=new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity;
        try {
            responseEntity= restTemplate.postForEntity(oauth2LoginGithub.getTokenUri(code), httpEntity, String.class);
        }catch (Exception e){
            log.warn (String.format("there are some errors happend when get token:%s",e.getMessage()));
            return new backResponse(responseEnum.LOGIN_FAILED.getStatusCode(),responseEnum.LOGIN_FAILED.getStatusDescription(),null);
        }
        log.info("successfully get token ");
        return new backResponse(responseEnum.LOGIN_SUCCESS.getStatusCode(),responseEnum.LOGIN_SUCCESS.getStatusDescription(),(Object) responseEntity.getBody());
    }
}
