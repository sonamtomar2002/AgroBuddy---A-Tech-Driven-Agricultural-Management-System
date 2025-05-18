package com.chatbotProject.chatbot.service;


import com.chatbotProject.chatbot.entity.UserSearch;
import com.chatbotProject.chatbot.repo.UserSearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSearchService {

    private final UserSearchRepository userSearchRepository;

    @Autowired
    public UserSearchService(UserSearchRepository userSearchRepository) {
        this.userSearchRepository = userSearchRepository;
    }

    public UserSearch saveUserSearch(UserSearch userSearch) {
        return userSearchRepository.save(userSearch);
    }
}
