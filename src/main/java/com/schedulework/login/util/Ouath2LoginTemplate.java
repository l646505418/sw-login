package com.schedulework.login.util;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:Li Jinming
 * @Description:template for oauth2 login
 * @date:2022-12-30
 */

@Data
@NoArgsConstructor
public abstract class Ouath2LoginTemplate {
    private String clientId;
    private String clientSecret;
    private String responseType;
    private authEndPoint AEP;
    private tokenEndPoint TEP;
    private   String[] scope;
    private   String baseAuthorizationUri;
    private   String baseTokenUri;
    private   String baseUserInfoUri;
    private   String baseJwkSetUri;

    public Ouath2LoginTemplate(Builder builder) {
        this.clientId = builder.clientId;
        this.clientSecret = builder.clientSecret;
        this.responseType = builder.responseType;
        this.AEP = builder.AEP;
        this.TEP = builder.TEP;
        this.scope = builder.scope;
        this.baseAuthorizationUri = builder.baseAuthorizationUri;
        this.baseTokenUri = builder.baseTokenUri;
        this.baseUserInfoUri = builder.baseUserInfoUri;
        this.baseJwkSetUri = builder.baseJwkSetUri;
    }


    public  String oauth2Login(){
        String AuthorizationUri=getAuthorizationUri();
        return doAuthorizationVia3Party(AuthorizationUri);
    }


    public abstract String doAuthorizationVia3Party(String url) ;

    public abstract  String getAuthorizationUri() ;
    public abstract String getTokenUri(String code);

    public abstract static  class Builder{
        private String clientId;
        private String clientSecret;
        private String responseType;
        private authEndPoint AEP;
        private tokenEndPoint TEP;
        private   String[] scope;
        private   String baseAuthorizationUri;
        private   String baseTokenUri;
        private   String baseUserInfoUri;
        private   String baseJwkSetUri;

        public Builder ClientId(String clientId){
            this.clientId=clientId;
            return this;
        }
        public Builder ClientSecret(String clientSecret){
            this.clientSecret=clientSecret;
            return this;
        }
        public Builder ResponseType(String responseType){
            this.responseType=responseType;
            return this;
        }
        public Builder AuthEndPoint(authEndPoint AEP){
            this.AEP=AEP;
            return this;
        }
        public Builder TokenEndPoint(tokenEndPoint TEP){
            this.TEP=TEP;
            return this;
        }
        public Builder Scope(String[] scope){
            this.scope=scope;
            return this;
        }
        public Builder BaseAuthorizationUri(String baseAuthorizationUri){
            this.baseAuthorizationUri=baseAuthorizationUri;
            return this;
        }
        public Builder BaseTokenUri(String baseTokenUri){
            this.baseTokenUri=baseTokenUri;
            return this;
        }
        public Builder BaseUserInfoUri(String baseUserInfoUri){
            this.baseUserInfoUri=baseUserInfoUri;
            return this;
        }
        public Builder BaseJwkSetUri(String baseJwkSetUri){
            this.baseJwkSetUri=baseJwkSetUri;
            return this;
        }
        public abstract Ouath2LoginTemplate build();
    }

}
