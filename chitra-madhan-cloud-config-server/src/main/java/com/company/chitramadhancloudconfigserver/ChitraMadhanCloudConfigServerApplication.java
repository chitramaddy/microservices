package com.company.chitramadhancloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ChitraMadhanCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChitraMadhanCloudConfigServerApplication.class, args);
	}

}
