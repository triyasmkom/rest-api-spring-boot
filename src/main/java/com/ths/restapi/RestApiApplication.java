package com.ths.restapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

	//membuat bean model mapper agar bisa lansung kita injek dengan autowired

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
