/*
 * package com.chatbotProject.chatbot.config;
 * 
 * import com.auth0.jwt.JWT; import com.auth0.jwt.algorithms.Algorithm; import
 * com.auth0.jwt.interfaces.DecodedJWT; import
 * org.springframework.stereotype.Component;
 * 
 * import java.util.Date;
 * 
 * @Component public class JwtUtil {
 * 
 * private String secretKey =
 * "f9d0f52f99f56f6db299bf7096c659cd9a431d6ebff6e7a279d05e280f76f36e"; //
 * Replace with a more secure key
 * 
 * // Generate a JWT token public String generateToken(String username) {
 * Algorithm algorithm = Algorithm.HMAC256(secretKey); // Secret key for signing
 * return JWT.create() .withSubject(username) // Add username as the subject
 * claim .withIssuedAt(new Date()) // Set issued at .withExpiresAt(new
 * Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Set expiration time (1
 * hour) .sign(algorithm); // Sign with HMAC algorithm }
 * 
 * // Extract username from the JWT token public String extractUsername(String
 * token) { DecodedJWT decodedJWT = decodeToken(token); return
 * decodedJWT.getSubject(); // Extract the subject (username) }
 * 
 * // Extract the expiration date from the JWT token public Date
 * extractExpiration(String token) { DecodedJWT decodedJWT = decodeToken(token);
 * return decodedJWT.getExpiresAt(); // Get the expiration date from the token }
 * 
 * // Decode the JWT token (helper method) private DecodedJWT decodeToken(String
 * token) { Algorithm algorithm = Algorithm.HMAC256(secretKey); // Recreate the
 * algorithm to decode return JWT.require(algorithm) .build() .verify(token); //
 * Verify and decode the token }
 * 
 * // Check if the token is expired public boolean isTokenExpired(String token)
 * { Date expirationDate = extractExpiration(token); return
 * expirationDate.before(new Date()); // Check if the expiration date is before
 * current date }
 * 
 * // Validate the JWT token (check subject and expiration) public boolean
 * validateToken(String token, String username) { return
 * (username.equals(extractUsername(token)) && !isTokenExpired(token)); } }
 */