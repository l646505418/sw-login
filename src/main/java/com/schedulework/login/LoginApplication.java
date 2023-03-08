package com.schedulework.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import com.schedulework.login.util.PropertySourceFactory;

@SpringBootApplication
@MapperScan("com.schedulework.login.mapper")
@PropertySource(value = "file:${config.dir}/application.yaml",factory = PropertySourceFactory.class)
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

}
