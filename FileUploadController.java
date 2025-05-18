package com.chatbotProject.chatbot.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

	@PostMapping
	public ResponseEntity<Map<String, String>> handleFileUpload(@RequestParam("files") MultipartFile[] files) {
	    Map<String, String> response = new HashMap<>();

	    if (files == null || files.length == 0) {
	        response.put("message", "No files uploaded");
	        return ResponseEntity.badRequest().body(response);
	    }

	    for (MultipartFile file : files) {
	        // Log file details
	        System.out.println("Received file: " + file.getOriginalFilename() + " (" + file.getSize() + " bytes)");

	        try {
	            // Example: Save file to a location on the server
	            // file.transferTo(new File("path/to/uploaded/file"));

	            response.put("message", "File " + file.getOriginalFilename() + " uploaded successfully");
	        } catch (Exception e) {
	            response.put("message", "Error uploading file " + file.getOriginalFilename());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
	    }

	    return ResponseEntity.ok(response);
	}

}
