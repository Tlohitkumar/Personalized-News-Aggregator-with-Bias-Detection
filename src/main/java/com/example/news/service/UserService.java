package com.example.news.service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.news.model.User;
import com.example.news.repository.UserRepository;

@Service
public class UserService {
	 
	@Autowired
	private UserRepository userRepository;
	
	public User registerUser(User user) {
		return userRepository.save(user);
	}
	
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
}



