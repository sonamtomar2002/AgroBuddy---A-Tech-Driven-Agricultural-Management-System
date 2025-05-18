package com.chatbotProject.chatbot.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "user_searches")
public class UserSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String searchTerm;
    private LocalDateTime searchTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	public LocalDateTime getSearchTime() {
		return searchTime;
	}
	public void setSearchTime(LocalDateTime searchTime) {
		this.searchTime = searchTime;
	}
	@Override
	public String toString() {
		return "UserSearch [id=" + id + ", username=" + username + ", searchTerm=" + searchTerm + ", searchTime="
				+ searchTime + "]";
	}

    
}
