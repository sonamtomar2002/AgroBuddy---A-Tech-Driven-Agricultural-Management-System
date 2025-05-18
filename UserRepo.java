package com.chatbotProject.chatbot.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chatbotProject.chatbot.entity.User;

@Repository

public interface UserRepo extends JpaRepository <User,String> {

	boolean existsByUname(String username);

	Optional<User> findByUname(String uname);
	 @Query("SELECT u FROM User u WHERE u.lastLogin IS NOT NULL AND u.lastLogout IS NOT NULL")
	List<User> findAllUserLogs();
	
	

	//User findbyUname(String uname);

}
