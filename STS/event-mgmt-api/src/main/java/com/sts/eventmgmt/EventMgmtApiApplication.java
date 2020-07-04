package com.sts.eventmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;

@SpringBootApplication
@EntityScan(basePackageClasses= {EventMgmtApiApplication.class, Jsr310Converters.class})
public class EventMgmtApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventMgmtApiApplication.class, args);
	}

}
