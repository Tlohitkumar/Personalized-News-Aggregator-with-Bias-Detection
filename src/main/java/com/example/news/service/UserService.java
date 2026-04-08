package com.example.news.service;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.news.config.JwtUtil;
import com.example.news.model.User;
import com.example.news.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
	 
	@Autowired
	private UserRepository userRepository;
	
	//normal user register
	
//	public User registerUser(User user) {
//		return userRepository.save(user);
//	}
	
	public List<User> getAllUsers() {
	    return userRepository.findAll();
	}
	
	public User getUserById(Long id) {
	    return userRepository.findById(id).orElse(null);
	}

	public void deleteUser(Long id) {
	    userRepository.deleteById(id);
	}
	
	public User updateUser(Long id, User newUser) {
	    User existing = userRepository.findById(id).orElse(null);

	    if (existing != null) {
	        existing.setName(newUser.getName());
	        existing.setEmail(newUser.getEmail());
	        existing.setPassword(newUser.getPassword());
	        return userRepository.save(existing);
	    }
	    return null;
	}
	
//	This is for normal login check
	
//	public User login(String email, String password) {
//	    User user = userRepository.findByEmail(email).orElse(null);
//
//	    if (user != null && user.getPassword().equals(password)) {
//	        return user;
//	    }
//	    return null;
//	}
	
	@Autowired
	private JwtUtil jwtUtil;

//	This is for normal login check with TOKEN
	
//	public String login(String email, String password) {
//	    User user = userRepository.findByEmail(email).orElse(null);
//
//	    if (user != null && user.getPassword().equals(password)) {
//	        return jwtUtil.generateToken(email);
//	    }
//	    return "Invalid Credentials";
//	}
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public User registerUser(User user) {
	    System.out.println("Password: " + user.getPassword()); // 👈 add this
	    user.setPassword(encoder.encode(user.getPassword()));
	    user.setRole("USER");
	    return userRepository.save(user);
	}
	
//	This is for normal Encrypted Login Check
	
	public String login(String email, String password) {
	    User user = userRepository.findByEmail(email).orElse(null);

	    if (user != null && encoder.matches(password, user.getPassword())) {
	    	String role = user.getRole() != null ? user.getRole() : "USER";
	        return jwtUtil.generateToken(user.getEmail(), user.getRole()); //day 10
	    }
	    
	    return "Invalid Credentials";
	}
	
	
	
}




