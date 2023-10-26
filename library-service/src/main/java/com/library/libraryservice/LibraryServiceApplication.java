package com.library.libraryservice;

import com.library.libraryservice.client.RetreiveMessageErrorDecoder;
import com.library.libraryservice.repository.LibraryRepository;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.DefaultFeignLoggerFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients // Beande gorebilmesi için
public class LibraryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryServiceApplication.class, args);
	}


	/*
	FEIGN CLIENT ERROR HANDLING

	// RetreiveMessageErrorDecoder spring context kullanabilsin diye..
	@Bean
	public ErrorDecoder errorDecoder() {
		return new RetreiveMessageErrorDecoder();
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
	 */
}
