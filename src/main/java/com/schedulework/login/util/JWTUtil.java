package com.schedulework.login.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author:Li Jinming
 * @Description:to jwt util tool
 * @date:2022-11-20
 */
//TODO:need amend for istio jwt
@Slf4j
public class JWTUtil {
    // Token过期时间90分钟
    private static final long EXPIRE_TIME = 90 * 60 * 1000;
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_PREFIX = "Bearer ";

//TODO:增加cache去存储token并进行验证
/***
 *@Date:2022-11-20
 @Pramas:* @Param token
 *@return java.lang.Boolean
 */
/**
 *@Date:2022-11-20
 @Pramas:* @Param username
 *@return java.lang.String
 */
    public static String createToken(String username){
        Algorithm algorithm=Algorithm.HMAC256(username);
        //withClaim用来存储加密的信息，withExpiresAt设置过期的时间点
        return JWT.create().withClaim("username",username).withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME)).sign(algorithm);
    }
    public static String getToken(String data){
        String token=data.split(" ")[1];
        return token.isBlank()?null:token;
    }


}
