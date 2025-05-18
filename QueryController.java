package com.chatbotProject.chatbot.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {

    @DeleteMapping("/api/deleteAllQueries")
    public ResponseEntity<String> deleteAllQueries() {
        // Logic to delete all queries from your database or storage
        // Example:
        // queryService.deleteAllQueries(); // Implement this method in your service

        return ResponseEntity.ok("All queries deleted successfully.");
    }
}