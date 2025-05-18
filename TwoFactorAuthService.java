package com.chatbotProject.chatbot.service;

import java.awt.image.BufferedImage;

import java.io.IOException;

import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Base64;

@Service
public class TwoFactorAuthService {

    @Autowired
    private QRCodeService qrCodeService;  // Injecting QRCodeService

    // Generates a new secret key for TOTP
    public String generateSecretKey() {
        String secretKey = Base32.random();  // Generates a random secret key
        System.out.println("Generated Secret Key: " + secretKey);  // Print the secret key to the console
        return secretKey;
    }

    // Generates OTP based on the secret key
    public String generateOTP(String secretKey) {
        Totp totp = new Totp(secretKey);
        return totp.now();  // Returns the current OTP
    }

    // Verifies the OTP entered by the user
    public boolean verifyOTP(String secretKey, String otp) {
        Totp totp = new Totp(secretKey);
        return totp.verify(otp);  // Verifies OTP against the secret key
    }

    // Generates a QR code from the secret key (calls the QRCodeService)
	/*
	 * public BufferedImage generateQRCode(String secretKey, String username) throws
	 * WriterException, IOException {
	 * System.out.println("Generating QR for Secret: " + secretKey +
	 * " and Username: " + username);
	 * 
	 * return qrCodeService.generateQRCode(secretKey, username); // Use
	 * QRCodeService to generate the QR code }
	 */
    public BufferedImage generateQRCode(String secretKey, String username) throws WriterException, IOException {
        try {
            System.out.println("Generating QR for Secret: " + secretKey + " and Username: " + username);
            return qrCodeService.generateQRCode(secretKey, username);
        } catch (Exception e) {
            System.out.println("Error generating QR code: " + e.getMessage());
            throw e; // Rethrow if necessary
        }
    }
    
    public String convertToBase64(BufferedImage image) throws IOException {
    	System.out.println("covertbase64");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);  // Write image to ByteArrayOutputStream in PNG format
        byte[] imageBytes = baos.toByteArray();  // Get byte array of the image
        String base64 = Base64.getEncoder().encodeToString(imageBytes);  // Encode image bytes to Base64 string
        System.out.println("Base64 Encoded QR Code: " + base64);  // Print Base64 string to console
        return base64;
    }
}
