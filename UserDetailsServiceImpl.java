/*
 * package com.chatbotProject.chatbot.config; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.chatbotProject.chatbot.entity.User; import
 * com.chatbotProject.chatbot.repo.UserRepo; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * @Service public class UserDetailsServiceImpl implements UserDetailsService {
 * 
 * private final UserRepo userRepo;
 * 
 * public UserDetailsServiceImpl(UserRepo userRepo) { this.userRepo = userRepo;
 * // Fix the assignment of the injected repository }
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { // Retrieve the user from the repository by
 * username User user = userRepo.findByUname(username).orElseThrow(() -> new
 * UsernameNotFoundException("User not found with username: " + username) );
 * 
 * // Convert user roles/permissions to authorities (if needed, you can modify
 * this logic to fetch roles from DB) List<SimpleGrantedAuthority> authorities =
 * new ArrayList<>();
 * 
 * // Example: If user is an admin (based on your isAdminUser method), grant
 * admin role if ("admin".equals(username)) { // Adjust this logic to match your
 * role-checking logic authorities.add(new
 * SimpleGrantedAuthority("ROLE_ADMIN")); } else { authorities.add(new
 * SimpleGrantedAuthority("ROLE_USER")); }
 * 
 * // Return an instance of UserDetails with username, password, and authorities
 * return new org.springframework.security.core.userdetails.User(
 * user.getUname(), user.getPassword(), authorities ); } }
 */