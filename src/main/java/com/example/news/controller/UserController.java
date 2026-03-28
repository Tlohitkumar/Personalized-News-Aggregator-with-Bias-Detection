package com.example.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.model.User;
import com.example.news.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")

public class UserController {
			
		@Autowired
	  private UserService userService;
		
		@PostMapping("/register")
		public User register(@RequestBody User user) {
			return userService.registerUser(user);
		}
}
