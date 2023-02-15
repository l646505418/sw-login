package com.schedulework.login.service.serviceImpl;


import com.schedulework.login.Entity.userLoginInfo;
import com.schedulework.login.mapper.userloginMapper;
import com.schedulework.login.service.userLoginService;
import com.schedulework.login.util.JWTUtil;
import com.schedulework.login.vo.backResponse;
import com.schedulework.login.vo.responseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:Li Jinming
 * @Description:
 * @date:2023-02-13
 */

@Service
public class userLoginServiceImpl implements userLoginService {
    @Autowired
    userloginMapper userloginMapper;
    @Override
    public backResponse doLogin(String username, String password) {
        userLoginInfo user=userloginMapper.findByNameAndPass(username,password);
        if(user==null){
            return new backResponse(responseEnum.USER_NOT_FOUND.getStatusCode(),responseEnum.USER_NOT_FOUND.getStatusDescription(),null);
        }

        return new backResponse(responseEnum.LOGIN_SUCCESS.getStatusCode(),responseEnum.LOGIN_SUCCESS.getStatusDescription(),(Object) user);
    }

    @Override
    public backResponse doLogon(String username, String password) {
        userLoginInfo userInfoInDb= userloginMapper.findByName(username);
        if(userInfoInDb!=null){
            new backResponse(responseEnum.LOGON_FAILED.getStatusCode(),responseEnum.LOGON_FAILED.getStatusDescription(),null);
        }
        userLoginInfo userInfo=new userLoginInfo(username,password);
        int userId=userloginMapper.insertUser(userInfo);
        String token= JWTUtil.createToken(username);
        return new backResponse(responseEnum.LOGON_SUCCESS.getStatusCode(),responseEnum.LOGON_SUCCESS.getStatusDescription(),new Object[]{userId,token});

    }
}
