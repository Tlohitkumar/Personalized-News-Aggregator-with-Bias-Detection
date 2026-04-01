package com.example.news.controller;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		
		@GetMapping
		public List<User> getAllUsers() {
		    return userService.getAllUsers();
		}
		
		@GetMapping("/{id}")
		public User getUserById(@PathVariable Long id) {
		    return userService.getUserById(id);
		}

		@DeleteMapping("/{id}")
		public String deleteUser(@PathVariable Long id) {
		    userService.deleteUser(id);
		    return "User deleted successfully";
		}
		
		@PutMapping("/{id}")
		public User updateUser(@PathVariable Long id, @RequestBody User user) {
		    return userService.updateUser(id, user);
		}
		
		
		//Validating User Details
		@PostMapping("/login")
		public String login(@RequestBody User user) {

		    User validUser = userService.login(user.getEmail(), user.getPassword());

		    if (validUser != null) {
		        return "Login Success ✅";
		    } else {
		        return "Invalid Email or Password ❌";
		    }
		}
}
