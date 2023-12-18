package com.base.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.base.admin.mapper")
public class BaseAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseAdminServerApplication.class, args);
	}

}
