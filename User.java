package com.chatbotProject.chatbot.entity;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "login")

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private String uname;
	private String password;
	
	
	private LocalDateTime lastLogin;
    private LocalDateTime lastLogout;
    private String secretKey;
    
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	public LocalDateTime getLastLogout() {
		return lastLogout;
	}
	
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
	/*
	 * @Override public String toString() { return "User [id=" + id + ", uname=" +
	 * uname + ", password=" + password + ", lastLogin=" + lastLogin +
	 * ", lastLogout=" + lastLogout + "]"; }
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", password=" + password + ", lastLogin=" + lastLogin
				+ ", lastLogout=" + lastLogout + ", secretKey=" + secretKey + "]";
	}
	
	public void setLastLogin(LocalDateTime lastLogin) {
	    this.lastLogin = lastLogin;
	}

	
	public void setLastLogout(LocalDateTime lastLogout) {
	    this.lastLogout = lastLogout;
	}
	
}
