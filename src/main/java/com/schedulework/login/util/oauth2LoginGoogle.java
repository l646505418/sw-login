package com.schedulework.login.util;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @author:Li Jinming
 * @Description:oauth2 login via google
 * @date:2022-12-30
 */
@Slf4j
@Data
public class oauth2LoginGoogle extends Ouath2LoginTemplate {
    private String grantType;
    private HashMap<String,String> scopeUrl;

    public oauth2LoginGoogle(Builder builder){
        super(builder);
        this.grantType="authorization_code";
        //should search sql to get real url
        scopeUrl=new HashMap<>();
        scopeUrl.put("email","https://mail.google.com/");
        scopeUrl.put("calendar","https://calendar.google.com/");

    }


    @Override
    public String doAuthorizationVia3Party(String url) {
        return url;
    }

    @Override
    public String getAuthorizationUri() {
        String[] scope=convertScope(this.getScope());
        String authorizationUri="";
        if("code".equals(this.getResponseType()))
            authorizationUri=String.format("%s?response_type=%s&client_id=%s&redirect_uri=%s&scope=%s",this.getBaseAuthorizationUri(),this.getResponseType(),getClientId(), this.getAEP().getRedirectUrl(),String.join("%20",scope));
        if("token".equals(this.getResponseType()))
            authorizationUri=String.format("%s?response_type=%s&client_id=%s&redirect_uri=%s&scope=%s",this.getBaseAuthorizationUri(),this.getResponseType(),getClientId(), this.getTEP().getRedirectUrl(),String.join("%20",scope));
        log.info(String.format("the authorization url is : %s",authorizationUri));
        return authorizationUri;
    }

    private String[] convertScope(String[] scope) {
        for (int i=0;i<scope.length;i++){
            scope[i]=scopeUrl.get(scope[i]);
        }
        return scope;
    }

    @Override
    public String getTokenUri(String code) {
        String tokenUri=String.format("%s?client_id=%s&client_secret=%s&grant_type=authorization_code&code=%s&redirect_uri=%s",this.getBaseTokenUri(),this.getClientId(),this.getClientSecret(),code,this.getTEP().getRedirectUrl());
        log.info(String.format("the token url is : %s",tokenUri));
        return tokenUri;
    }

    @Override
    public HashMap<String, String> getAccessToken(String code) {
        return null;
    }

    @Override
    public HashMap<String, String> parseTokenResponse(String response) {
        return null;
    }

    @Override
    public HashMap<String, String> getUserInfo(String token) {
        return null;
    }


    public static class Builder extends Ouath2LoginTemplate.Builder{
        @Override
        public Ouath2LoginTemplate build() {
            return new oauth2LoginGoogle(this);
        }
    }
}
