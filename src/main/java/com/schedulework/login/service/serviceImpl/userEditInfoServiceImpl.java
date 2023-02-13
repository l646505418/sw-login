package com.schedulework.login.service.serviceImpl;


import com.schedulework.login.Entity.userLoginInfo;
import com.schedulework.login.mapper.userloginMapper;
import com.schedulework.login.service.userEditInfoService;
import com.schedulework.login.vo.backResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:Li Jinming
 * @Description:
 * @date:2023-02-13
 */

@Service
public class userEditInfoServiceImpl implements userEditInfoService {
    @Autowired
    userloginMapper userloginMapper;


    @Override
    public backResponse editProtofile(userLoginInfo newUserInfo) {
        userLoginInfo oldUserInfo= userloginMapper.findById(newUserInfo.getId());
        return null;
    }
}
