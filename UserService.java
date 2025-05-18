package com.chatbotProject.chatbot.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import com.chatbotProject.chatbot.entity.User;
import com.chatbotProject.chatbot.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void saveUser(User user) {
        userRepo.save(user);
    }
    // Method to get a user by username (for login)
    public Optional<User> getUserByUsername(String username) {
        return userRepo.findByUname(username);  // Retrieve user by username from the database
    }

    

    // Method to enable 2FA for the user
    public void enable2FA(User user, String secretKey) {
        user.setSecretKey(secretKey);  // Set the generated secret key for the user
        saveUser(user);  // Save the user with the new secret key
    }

    // Method to check if the user has 2FA enabled
    public boolean is2FAEnabled(User user) {
        return user.getSecretKey() != null && !user.getSecretKey().isEmpty();  // Check if secret key exists
    }

    // Method to update the last login time for the user
    public void updateLastLogin(User user) {
        user.setLastLogin(java.time.LocalDateTime.now());  // Set the current time as last login time
        saveUser(user);  // Save the updated user record
    }

    // Method to update the last logout time for the user
    public void updateLastLogout(User user) {
        user.setLastLogout(java.time.LocalDateTime.now());  // Set the current time as last logout time
        saveUser(user);  // Save the updated user record
    }


	public List<User> getAllUsers() {
		return userRepo.findAll();
		// TODO Auto-generated method stub
		
	}

	public List<User> getAllUserLogs() {
		// TODO Auto-generated method stub
		return userRepo.findAllUserLogs();
	}
	
	
}