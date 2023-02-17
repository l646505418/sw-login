package com.schedulework.login.util;


/**
 * @author:Li Jinming
 * @Description:
 * @date:2023-02-17
 */


public class oauth2LoginGithub extends Ouath2LoginTemplate{
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
        ,String.join(",",this.getScope()));
        return authorizationUri;
    }


    @Override
    public String getTokenUri(String code) {
        String tokenUri=null;
        tokenUri=String.format("%s?client_id=%s&client_secret=%s&code=%s&redirect_uri=%s",this.getBaseTokenUri(),this.getClientId()
                ,this.getClientSecret(),code,this.getAEP().getRedirectUrl());
        return tokenUri;
    }

    public static class Builder extends Ouath2LoginTemplate.Builder{
        @Override
        public Ouath2LoginTemplate build() {
            return new oauth2LoginGithub(this);
        }
    }
}
