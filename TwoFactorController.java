/*
 * package com.chatbotProject.chatbot.controller;
 * 
 * import java.awt.image.BufferedImage; import java.io.IOException;
 * 
 * import javax.servlet.http.HttpSession;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping;
 * 
 * import com.chatbotProject.chatbot.entity.User; import
 * com.chatbotProject.chatbot.repo.UserRepo; import
 * com.chatbotProject.chatbot.service.TwoFactorAuthService; import
 * com.google.zxing.WriterException;
 * 
 * @Controller public class TwoFactorController {
 * 
 * @Autowired private UserRepo userRepo;
 * 
 * @Autowired private TwoFactorAuthService twoFactorAuthService; // Use the
 * TwoFactorAuthService
 * 
 * @GetMapping("/enable2fa") public String enable2faPage(Model model,
 * HttpSession session) throws WriterException, IOException { User loggedInUser
 * = (User) session.getAttribute("loggedInUser");
 * 
 * if (loggedInUser == null) { return "redirect:/"; // Redirect to login if no
 * user is logged in }
 * 
 * // Generate a secret key for the user and store it in the database String
 * secretKey = twoFactorAuthService.generateSecretKey();
 * loggedInUser.setSecretKey(secretKey); userRepo.save(loggedInUser);
 * 
 * // Generate the QR code for the user to scan BufferedImage qrCodeImage =
 * twoFactorAuthService.generateQRCode(secretKey, loggedInUser.getUname());
 * 
 * // Convert BufferedImage to Base64 string String base64QrCode =
 * twoFactorAuthService.convertToBase64(qrCodeImage);
 * System.out.println("Generated QR Code (Base64): " + base64QrCode);
 * 
 * model.addAttribute("qrCode", base64QrCode); model.addAttribute("secret",
 * secretKey);
 * 
 * return "setup2fa"; // A page to display the QR code and OTP form } }
 */