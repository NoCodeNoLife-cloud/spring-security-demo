package com.cod.controller;
// Copyright (c) 2023, NoCodeNoLife-cloud. All rights reserved.
// Author: nightCrawler ( NoCodeNoLife )
// Created: 2023/10/27 21:19
import com.cod.entity.Users;
import com.cod.service.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * WebController
 * @author admin
 */
@Slf4j
@Controller
public class WebController {
	/**
	 * manager
	 */
	@Autowired
	private UserDetailsManager manager;
	/**
	 * encoder
	 */
	@Autowired
	private PasswordEncoder encoder;
	/**
	 * myUserDetailsService
	 */
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	/**
	 * authenticationManager
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * changePassword
	 * @param oldPassword     oldPassword
	 * @param newPassword     newPassword
	 * @param confirmPassword confirmPassword
	 * @return String
	 */
	@RequestMapping("/user/changePassword")
	public String changePassword(String oldPassword, String newPassword, String confirmPassword) {
		// Check if the new password and confirm password are the same
		if (newPassword.equals(confirmPassword)) {
			// Get the current authenticated user
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String name = user.getUsername();

			// Fetch the user details from the database
			Users users = myUserDetailsService.selectByPrimaryKey(name);

			// Check if the old password matches with the stored password in the database
			if (encoder.matches(oldPassword, users.getPassword())) {
				// If it matches, update the password in the database with the new password
				users.setPassword(encoder.encode(newPassword));
				myUserDetailsService.updateByPrimaryKey(users);

				// Redirect to login page after successful password change
				return "redirect:/index";
			}

			// If old password doesn't match, redirect to change password page
			return "redirect:/index";
		}

		// If new password and confirm password doesn't match, redirect to change password page
		return "redirect:/index";
	}

	/**
	 * register
	 * @param username username
	 * @param password password
	 * @return String
	 */
	@RequestMapping("/user/register")
	public String register(String username, String password) {
		if (manager.userExists(username)) {
			return "redirect:/login";
		}
		manager.createUser(User.withUsername(username).password(encoder.encode(password)).roles("USER").build());
		return "redirect:/login";
	}

	/**
	 * changePassword
	 * @return String
	 */
	@RequestMapping("/changePassword")
	public String changePassword() {
		return "changePassword";
	}

	/**
	 * register
	 * @return String
	 */
	@RequestMapping("/register")
	public String register() {
		return "register";
	}

	/**
	 * loging
	 * @return String
	 */
	@RequestMapping("/login")
	public String loging() {
		return "login";
	}

	/**
	 * index
	 * @return String
	 */
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
