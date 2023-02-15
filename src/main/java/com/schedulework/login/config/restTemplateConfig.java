package com.schedulework.login.config;


import com.schedulework.login.util.authEndPoint;
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
                .TokenEndPoint(new tokenEndPoint("http://localhst:8090/api/calback"))
                .Scope(new String[]{"email"})
                .BaseAuthorizationUri("https://accounts.google.com/o/oauth2/auth")
                .BaseTokenUri("https://oauth2.googleapis.com/token")
                .BaseJwkSetUri("https://www.googleapis.com/oauth2/v1/certs")
                .BaseUserInfoUri("https://www.googleapis.com/oauth2/v1/userinfo")
                .build();
    }

}
