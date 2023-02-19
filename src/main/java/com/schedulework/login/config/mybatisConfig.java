package com.schedulework.login.config;


//import jakarta.annotation.Resource;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author:Li Jinming
 * @Description:
 * @date:2023-02-13
 */
//@Slf4j
//@Configuration
//@Setter
//public class mybatisConfig {
//    @Autowired
//    Environment environment;
////    private String env;
//
//    @Bean
//    public SqlSessionFactory swSqlSession() {
//        InputStream is;
//        SqlSessionFactory sqlSessionFactory=null;
//        try {
//            is = Resources.getResourceAsStream("mybatis-config.xml");
//            sqlSessionFactory=new SqlSessionFactoryBuilder().build(is,environment.getActiveProfiles()[0]);
//        } catch (Exception e) {
//            log.error(String.format("there are something error happend when init sqlSessionFactory:%s", e.getMessage()));
//            throw e;
//        }
//        finally {
//            return sqlSessionFactory;
//        }
//    }
//}
