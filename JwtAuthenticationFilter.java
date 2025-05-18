/*
 * package com.chatbotProject.chatbot.config;
 * 
 * import org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.context.SecurityContextHolder; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.filter.OncePerRequestFilter;
 * 
 * import javax.servlet.FilterChain; import javax.servlet.ServletException;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import java.io.IOException;
 * 
 * @Component // This ensures the filter is registered as a Spring bean public
 * class JwtAuthenticationFilter extends OncePerRequestFilter {
 * 
 * private final JwtTokenProvider jwtTokenProvider; // Updated class name for
 * token provider private final UserDetailsService userDetailsService;
 * 
 * public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
 * UserDetailsService userDetailsService) { this.jwtTokenProvider =
 * jwtTokenProvider; this.userDetailsService = userDetailsService; }
 * 
 * @Override protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response, FilterChain filterChain) throws
 * ServletException, IOException {
 * 
 * // Extract token from Authorization header String token =
 * request.getHeader("Authorization");
 * 
 * if (token != null && token.startsWith("Bearer ")) { token =
 * token.substring(7); // Remove "Bearer " prefix String username =
 * jwtTokenProvider.extractUsername(token); // Extract username from the token
 * 
 * if (username != null &&
 * SecurityContextHolder.getContext().getAuthentication() == null) { // If the
 * token is valid, load the user details from the database if
 * (jwtTokenProvider.validateToken(token, username)) { UserDetails userDetails =
 * userDetailsService.loadUserByUsername(username);
 * 
 * // Create authentication token with roles/authorities
 * UsernamePasswordAuthenticationToken authenticationToken = new
 * UsernamePasswordAuthenticationToken( userDetails, null,
 * userDetails.getAuthorities() );
 * 
 * // Set the authentication in SecurityContextHolder
 * SecurityContextHolder.getContext().setAuthentication(authenticationToken); }
 * } }
 * 
 * // Proceed with the next filter in the chain filterChain.doFilter(request,
 * response); } }
 */