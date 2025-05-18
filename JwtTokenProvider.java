/*
 * package com.chatbotProject.chatbot.config;
 * 
 * 
 * import com.auth0.jwt.JWT; import com.auth0.jwt.interfaces.DecodedJWT; import
 * com.auth0.jwt.interfaces.JWTVerifier; import
 * com.auth0.jwt.algorithms.Algorithm; import
 * org.springframework.stereotype.Component;
 * 
 * import java.util.Date;
 * 
 * @Component public class JwtTokenProvider {
 * 
 * private String jwtSecret =
 * "f9d0f52f99f56f6db299bf7096c659cd9a431d6ebff6e7a279d05e280f76f36e"; //
 * Replace with your actual secret key private long jwtExpirationInMs =
 * 86400000; // Token expiration time (1 day)
 * 
 * // Extract username (subject) from the JWT token public String
 * extractUsername(String token) { DecodedJWT decodedJWT = JWT.decode(token); //
 * Decode the token return decodedJWT.getSubject(); // Return the subject
 * (username) }
 * 
 * // Validate the JWT token public boolean validateToken(String token, String
 * username) { try { DecodedJWT decodedJWT = decodeToken(token); return
 * (decodedJWT.getSubject().equals(username) && !isTokenExpired(decodedJWT)); }
 * catch (Exception e) { return false; // Token is invalid or expired } }
 * 
 * // Decode JWT token private DecodedJWT decodeToken(String token) { Algorithm
 * algorithm = Algorithm.HMAC256(jwtSecret); // Secret key for signing
 * JWTVerifier verifier = JWT.require(algorithm).build(); // Create JWT verifier
 * return verifier.verify(token); // Verify the token and decode it }
 * 
 * // Check if the token has expired private boolean isTokenExpired(DecodedJWT
 * decodedJWT) { Date expirationDate = decodedJWT.getExpiresAt(); return
 * expirationDate.before(new Date()); // Compare expiration date with current
 * time } }
 */