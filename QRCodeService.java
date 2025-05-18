package com.chatbotProject.chatbot.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

@Service
public class QRCodeService {

	 // Generates a QR Code as BufferedImage from the secret key and username
    public BufferedImage generateQRCode(String secret, String username) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // URL encode the username to prevent issues with special characters
        String encodedUsername = URLEncoder.encode(username, StandardCharsets.UTF_8.toString());

        // Generate the URL for Google Authenticator with the username and secret key
        String url = "otpauth://totp/chatbot:" + encodedUsername + "?secret=" + secret + "&issuer=ChatbotProject";

        // Generate the QR code
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200);

        // Convert BitMatrix to BufferedImage
        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 200; x++) {
            for (int y = 0; y < 200; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF); // Black or White for QR code
            }
        }

        return bufferedImage; // Return the generated QR code as a BufferedImage
    }
}

