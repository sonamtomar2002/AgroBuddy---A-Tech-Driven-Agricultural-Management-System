/*
 * package com.chatbotProject.chatbot.controller; import
 * java.time.LocalDateTime; import java.time.format.DateTimeFormatter; import
 * java.util.Map;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.chatbotProject.chatbot.entity.UserSearch; import
 * com.chatbotProject.chatbot.service.UserSearchService;
 * 
 * @RestController
 * 
 * @RequestMapping("/api") public class UserSearchController {
 * 
 * private final UserSearchService userSearchService;
 * 
 * @Autowired public UserSearchController(UserSearchService userSearchService) {
 * this.userSearchService = userSearchService; }
 * 
 * @PostMapping("/storeSearch") public ResponseEntity<String>
 * storeSearchTerm(@RequestBody Map<String, Object> json) { try { String uname =
 * (String) json.get("uname"); // Extract username from JSON body String
 * searchTerm = (String) json.get("searchTerm"); String searchTimeString =
 * (String) json.get("searchTime");
 * System.out.println("111111111111111"+searchTerm);
 * 
 * // Parse searchTime string to LocalDateTime LocalDateTime searchTime =
 * LocalDateTime.parse(searchTimeString, DateTimeFormatter.ISO_DATE_TIME);
 * 
 * UserSearch userSearch = new UserSearch(); userSearch.setUsername(uname); //
 * Set username in UserSearch object userSearch.setSearchTerm(searchTerm);
 * userSearch.setSearchTime(searchTime);
 * 
 * UserSearch savedSearch = userSearchService.saveUserSearch(userSearch);
 * System.out.println("Stored user search: " + savedSearch.toString()); return
 * ResponseEntity.ok("Search term stored successfully with ID: " +
 * savedSearch.getId()); } catch (Exception e) { e.printStackTrace(); return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
 * body("Failed to store search term"); } } }
 */

package com.chatbotProject.chatbot.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.chatbotProject.chatbot.entity.UserSearch;
import com.chatbotProject.chatbot.service.UserSearchService;

@RestController
@RequestMapping("/api")
public class UserSearchController {

    private final UserSearchService userSearchService;
    private final RestTemplate restTemplate;

    @Autowired
    public UserSearchController(UserSearchService userSearchService, RestTemplate restTemplate) {
        this.userSearchService = userSearchService;
        this.restTemplate = restTemplate;
    }

    // Existing endpoint to store search term in the database
    @PostMapping("/storeSearch")
    public ResponseEntity<String> storeSearchTerm(@RequestBody Map<String, Object> json) {
        try {
            String uname = (String) json.get("uname"); // Extract username from JSON body
            String searchTerm = (String) json.get("searchTerm");
            String searchTimeString = (String) json.get("searchTime");

            // Parse searchTime string to LocalDateTime
            LocalDateTime searchTime = LocalDateTime.parse(searchTimeString, DateTimeFormatter.ISO_DATE_TIME);

            UserSearch userSearch = new UserSearch();
            userSearch.setUsername(uname); // Set username in UserSearch object
            userSearch.setSearchTerm(searchTerm);
            userSearch.setSearchTime(searchTime);

            UserSearch savedSearch = userSearchService.saveUserSearch(userSearch);

            return ResponseEntity.ok("Search term stored successfully with ID: " + savedSearch.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to store search term");
        }
    }

    // New endpoint to forward user query to the external API and return the response
    @PostMapping("/query")
    public ResponseEntity<String> getBotResponse(@RequestBody Map<String, String> request) {
        try {
            String query = request.get("query");

            // Store the query in the database
            String uname = "defaultUser"; // Replace this with actual logic to fetch username (e.g., from session)
            String searchTime = LocalDateTime.now().toString(); // Use current time
            storeQueryInDatabase(uname, query, searchTime);

            // Call the external API with the query
            String externalApiUrl = "http://10.199.207.92:5000/query";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create the payload for the external API
            String payload = "{\"query\": \"" + query + "\"}";
            HttpEntity<String> entity = new HttpEntity<>(payload, headers);

            // Send the request to the external API
            ResponseEntity<String> externalApiResponse = restTemplate.exchange(
                    externalApiUrl, HttpMethod.POST, entity, String.class);

            // Return the external API's response
            return ResponseEntity.ok(externalApiResponse.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"response\": \"Sorry, something went wrong.\"}");
        }
    }

    // Helper method to store the query in the database
    private void storeQueryInDatabase(String uname, String query, String searchTime) {
        try {
            LocalDateTime searchTimeParsed = LocalDateTime.parse(searchTime, DateTimeFormatter.ISO_DATE_TIME);
            UserSearch userSearch = new UserSearch();
            userSearch.setUsername(uname);
            userSearch.setSearchTerm(query);
            userSearch.setSearchTime(searchTimeParsed);
            userSearchService.saveUserSearch(userSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
