package com.chatbotProject.chatbot.repo;



import com.chatbotProject.chatbot.entity.UserSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSearchRepository extends JpaRepository<UserSearch, Long> {
    // Define custom queries if needed
}
