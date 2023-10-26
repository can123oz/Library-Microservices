package com.example.eurokaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //bunu gorunce spring otomatik oluşturması gerekeni biliyor comfiglerden sonra
public class EurokaServerApplication {
	// http://localhost:9761/ adres bu
	public static void main(String[] args) {
		SpringApplication.run(EurokaServerApplication.class, args);
	}

}
