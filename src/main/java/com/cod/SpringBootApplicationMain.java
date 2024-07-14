package com.cod;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * SpringBootApplicationInitiator
 * @author admin
 */
@Slf4j
@SpringBootApplication
@EnableWebSecurity
public class SpringBootApplicationMain {
	/**
	 * main
	 * @param args args
	 */
	@SneakyThrows
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootApplicationMain.class, args);
	}
}