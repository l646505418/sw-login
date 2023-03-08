package com.schedulework.login.util;


import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:Li Jinming
 * @Description:
 * @date:2023-02-17
 */

@Slf4j
public class oauth2LoginGithub extends Ouath2LoginTemplate{


    @Value("${oauth2.github.userInfoUrl}")
    private String  userInfoUrl;
    private Gson gson=new Gson();
    @Autowired
    RestTemplate restTemplate;
    public oauth2LoginGithub(Builder builder) {
        super(builder);
    }
    @Override
    public String doAuthorizationVia3Party(String url) {
        return url;
    }

    @Override
    public String getAuthorizationUri() {
        String authorizationUri=null;
        authorizationUri=String.format("%s?client_id=%s&redirect_uri=%s&scope=%s",this.getBaseAuthorizationUri(),this.getClientId()
        ,this.getAEP().getRedirectUrl(),String.join(",",this.getScope()));
        return authorizationUri;
    }


    @Override
    public String getTokenUri(String code) {
        String tokenUri=null;
        tokenUri=String.format("%s?client_id=%s&client_secret=%s&code=%s&redirect_uri=%s",this.getBaseTokenUri(),this.getClientId()
                ,this.getClientSecret(),code,this.getAEP().getRedirectUrl());
        return tokenUri;
    }

    @Override
    public HashMap<String, String> getAccessToken(String code) {
        HttpHeaders httpHeaders=new HttpHeaders();
        HttpEntity<MultiValueMap<String,String>> httpEntity=new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity;
        try {
            responseEntity= restTemplate.postForEntity(this.getTokenUri(code), httpEntity, String.class);
        }catch (Exception e){
            log.warn (String.format("there are some errors happend when get token:%s",e.getMessage()));
            throw e;
        }
        log.info("start parse token string");
        return parseTokenResponse(responseEntity.getBody());
    }

    @Override
    public HashMap<String, String> parseTokenResponse(String response) {
        HashMap<String, String> res = null;
        if (response.contains("&")) {
            String[] fields = response.split("&");
            res = new HashMap<>((int) (fields.length / 0.75 + 1));
            for (String field : fields) {
                if (field.contains("=")) {
                    String[] keyValue = field.split("=");
                    res.put(urlDecode(keyValue[0]), keyValue.length == 2 ? urlDecode(keyValue[1]) : null);
                }
            }
        } else {
            res = new HashMap<>(0);
        }
        return res;
    }

    @Override
    public HashMap<String, String> getUserInfo(String token) {
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Authorization","Bearer "+token);
        HttpEntity<MultiValueMap<String,String>> httpEntity=new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity;
        try {
            responseEntity= restTemplate.postForEntity(userInfoUrl,httpEntity,String.class);
        }catch (Exception e){
            log.warn (String.format("there are some errors happend when get userInfo:%s",e.getMessage()));
            throw e;
        }
        log.info("start parse  user Info string");
        String userInfo=responseEntity.getBody();
        HashMap<String,String> info=gson.fromJson(userInfo, HashMap.class);
        return info;
    }

    private String urlDecode(String value)  {
        if (value == null) {
            return "";
        }
        try {
            return URLDecoder.decode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        return "";
    }
    public static class Builder extends Ouath2LoginTemplate.Builder{
        @Override
        public Ouath2LoginTemplate build() {
            return new oauth2LoginGithub(this);
        }
    }
}
