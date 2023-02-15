package com.schedulework.login.service.serviceImpl;


import com.schedulework.login.Entity.userLoginInfo;
import com.schedulework.login.mapper.userloginMapper;
import com.schedulework.login.service.userEditInfoService;
import com.schedulework.login.vo.backResponse;
import com.schedulework.login.vo.responseEnum;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author:Li Jinming
 * @Description:
 * @date:2023-02-13
 */
@Slf4j
@Service
public class userEditInfoServiceImpl implements userEditInfoService {
    @Autowired
    userloginMapper userloginMapper;


    @Override
    public backResponse editProtofile(userLoginInfo newUserInfo) {
        if(newUserInfo==null){
            return new backResponse(responseEnum.BAD_REQUEST.getStatusCode(),responseEnum.BAD_REQUEST.getStatusDescription(), null);
        }
        log.info(String.format("query user info for user:%s",newUserInfo.getId()));
        userLoginInfo oldUserInfo=null;
        try {
             oldUserInfo= userloginMapper.findById(newUserInfo.getId());
        }catch (Exception e){
            log.error(String.format("there are some error happend:%s",e.getMessage()));
        }
        if(oldUserInfo==null){
            log.warn(String.format("cannot found user info for user:%s",newUserInfo.getId()));
            return new backResponse(responseEnum.EDIT_FAILED.getStatusCode(),responseEnum.EDIT_FAILED.getStatusDescription(), null);
        }
        log.info(String.format("successfully query user info and start compare and enrich"));
        compAndEnrich(oldUserInfo,newUserInfo);
        log.info("update protofile for user");
        userloginMapper.updateUserInfo(oldUserInfo);
        return new backResponse(responseEnum.EDIT_SUCCESS.getStatusCode(),responseEnum.EDIT_SUCCESS.getStatusDescription(), oldUserInfo);

    }


    private void compAndEnrich(userLoginInfo oldUserInfo, userLoginInfo newUserInfo){
        CompletableFuture completableFuture=CompletableFuture.runAsync(()->{
            log.info("compare and enrich headImage");
            oldUserInfo.setHeadImage(newUserInfo.getHeadImage()==null? newUserInfo.getHeadImage() : oldUserInfo.getHeadImage());
        });
    }
}
