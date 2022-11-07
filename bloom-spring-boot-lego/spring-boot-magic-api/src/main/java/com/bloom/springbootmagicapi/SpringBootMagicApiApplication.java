package com.bloom.springbootmagicapi;

import cn.hutool.http.HttpUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableOpenApi
public class SpringBootMagicApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMagicApiApplication.class, args);
	}

}
