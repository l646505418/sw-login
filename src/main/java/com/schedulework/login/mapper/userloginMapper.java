package com.schedulework.login.mapper;
import com.schedulework.login.Entity.userLoginInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.schedulework.login.Entity.*;

/**
 * @author:Li Jinming
 * @Description:
 * @date:2023-02-13
 */

@Mapper
public interface userloginMapper {
    public userLoginInfo findByNameAndPass(@Param("username") String username, @Param("password") String password);
    public int insertUser(@Param("userInfo") userLoginInfo userInfo);
    public userLoginInfo findByName(@Param("username") String username);
    public int updateUserInfo(@Param("userInfo") userLoginInfo userInfo);
    public userLoginInfo findById(@Param("id") Long Id);
}
