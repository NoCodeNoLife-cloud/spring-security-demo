package com.cod.config;
// ============================================================
// Copyright (c) 2024, NoCodeNoLife-cloud. All rights reserved.
// Author: nightCrawler ( NoCodeNoLife )
// Created: 2024/04/04 16:16
// ============================================================
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.sql.DataSource;

/**
 * SecurityConfiguration
 * @author admin
 */
@Slf4j
@Configuration
public class SpringSecurityConfiguration {
	/**
	 * passwordEncoder
	 * @return PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		// Setting the encryption algorithm
		return new BCryptPasswordEncoder();
	}

	/**
	 * userDetailsService
	 * @param dataSource            dataSource
	 * @param authenticationManager authenticationManager
	 * @return UserDetailsManager
	 */
	@Bean
	public UserDetailsManager userDetailsService(DataSource dataSource, AuthenticationManager authenticationManager) {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);// Jdbc user management service, based on the same table structure as its parent class, JdbcDaoImpl.Provides CRUD operations for both users and groups.
		manager.setAuthenticationManager(authenticationManager);
		return manager;
	}

	/**
	 * authenticationManager
	 * @param dataSource dataSource
	 * @param encoder    encoder
	 * @return AuthenticationManager
	 */
	@Bean
	public AuthenticationManager authenticationManager(DataSource dataSource, PasswordEncoder encoder) {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);// Jdbc user management service, based on the same table structure as its parent class, JdbcDaoImpl.Provides CRUD operations for both users and groups.
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();// Used to handle authentication based on Data Access Objects (DAOs)
		provider.setUserDetailsService(manager);
		provider.setPasswordEncoder(encoder);
		return new ProviderManager(provider);
	}

	/**
	 * filterChain
	 * @param http http
	 * @return SecurityFilterChain
	 * @throws Exception Exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// Save request between two authentication attempts
		HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
		requestCache.setMatchingRequestParameterName("continue");

		http.requestCache((cache) -> {
			cache.requestCache(requestCache);
		});

		// Perform authorization settings for the specified URL path, including "/user/login", "/login", "/user/register", "/register", "/css/**", "/img/** ", all requests under these paths are allowed (i.e. no authentication is required)
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/user/login", "/login", "/user/register", "/register", "/css/**", "/asset/**").permitAll().anyRequest().authenticated()); // For all other requests, authentication is required

		// Set related configurations for form login
		http.formLogin(login -> login.loginPage("/login") // URL of the login page
				.loginProcessingUrl("/user/login") // URL for processing login requests
				.failureUrl("/login?error") // URL to jump to after login failure
				.defaultSuccessUrl("/index") // URL that jumps by default after successful login
				.permitAll()); // All users are allowed to access the login page

		// Disable CSRF (cross-site request forgery) protection
		http.csrf(AbstractHttpConfigurer::disable);

		return http.build(); // Build and return the HttpSecurity object
	}
}
