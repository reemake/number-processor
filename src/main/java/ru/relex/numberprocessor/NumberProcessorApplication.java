package ru.relex.numberprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NumberProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(NumberProcessorApplication.class, args);
	}

}
