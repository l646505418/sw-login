package com.schedulework.login.config;


import com.schedulework.login.util.authEndPoint;
import com.schedulework.login.util.oauth2LoginGithub;
import com.schedulework.login.util.oauth2LoginGoogle;
import com.schedulework.login.util.tokenEndPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author:Li Jinming
 * @Description:restTemplate config
 * @date:2022-12-30
 */

@Configuration
public class restTemplateConfig {


    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }


    @Bean
    public oauth2LoginGoogle oauth2LoginGoogleBean(){
        return (oauth2LoginGoogle) new oauth2LoginGoogle.Builder()
                .ClientId("677351595889-ruohjcv9v2eibnnino8g45ilcand5s4l.apps.googleusercontent.com")
                .ClientSecret("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")
                .ResponseType("code")
                .AuthEndPoint(new authEndPoint("http://localhost:8090/api/callback"))
                .Scope(new String[]{"email"})
                .BaseAuthorizationUri("https://accounts.google.com/o/oauth2/auth")
                .BaseTokenUri("https://oauth2.googleapis.com/token")
                .BaseJwkSetUri("https://www.googleapis.com/oauth2/v1/certs")
                .BaseUserInfoUri("https://www.googleapis.com/oauth2/v1/userinfo")
                .build();
    }
    @Bean
    public oauth2LoginGithub oauth2LoginGithubBean(){
        return (oauth2LoginGithub) new oauth2LoginGithub.Builder()
                .ClientId("48c953032195a367f00a")
                .ClientSecret("2f5ccdd3c80263c4a87f2641f4c3ed0b9fc00b54")
                .AuthEndPoint(new authEndPoint("http://localhost:9001/github/callback"))
                .Scope(new String[]{"user,repo"})
                .BaseAuthorizationUri("https://github.com/login/oauth/authorize")
                .BaseTokenUri("https://github.com/login/oauth/access_token")
                .build();
    }

}
