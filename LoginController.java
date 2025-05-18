/*
 * package com.chatbotProject.chatbot.controller;
 * 
 * import java.time.LocalDateTime; import java.util.List; import
 * java.util.Optional;
 * 
 * import javax.servlet.http.HttpSession;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.ResponseBody;
 * 
 * import com.chatbotProject.chatbot.entity.User; import
 * com.chatbotProject.chatbot.repo.UserRepo; import
 * com.chatbotProject.chatbot.service.UserService;
 * 
 * 
 * 
 * @Controller public class LoginController {
 * 
 * @Autowired private UserService userService;
 * 
 * @Autowired(required = true ) private UserRepo repo;
 * 
 * @GetMapping("/") public String login(Model model, @RequestParam(name =
 * "error", required = false) String error) { User user = new User();
 * model.addAttribute("user", user); model.addAttribute("error", error); return
 * "login"; }
 * 
 * @PostMapping("/userLogin") public String loginUser(@ModelAttribute("user")
 * User user, Model model, HttpSession session) { String uname =
 * user.getUname(); Optional<User> userData = repo.findByUname(uname); if
 * (userData.isPresent() &&
 * user.getPassword().equals(userData.get().getPassword())) { // Save login time
 * to the database User loggedInUser = userData.get();
 * loggedInUser.setLastLogin(LocalDateTime.now());
 * userService.saveUser(loggedInUser);
 * 
 * session.setAttribute("loggedInUser", loggedInUser); boolean isAdmin =
 * isAdminUser(uname); if (isAdmin) { model.addAttribute("isAdmin", true); }
 * return "home"; } else { model.addAttribute("error",
 * "Incorrect username or password."); return "login"; } }
 * 
 * @PostMapping("/createUser") public ResponseEntity<String>
 * createUser(@RequestBody User user) { System.out.println("Received user: " +
 * user); userService.saveUser(user); System.out.println("User saved: " + user);
 * return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
 * }
 * 
 * 
 * private boolean isAdminUser(String uname) { // Implement logic to check if
 * the user is an admin // For example, check if the username is "admin" return
 * "admin".equals(uname); // Replace "admin" with your admin username }
 * 
 * 
 * @GetMapping("/viewUsers")
 * 
 * @ResponseBody public List<User> viewUsers(Model model) { List<User> users =
 * userService.getAllUsers(); System.out.println("Users saved: " + users);
 * return users; }
 * 
 * @GetMapping("/logout") public String logout(HttpSession session) { User
 * loggedInUser = (User) session.getAttribute("loggedInUser"); if (loggedInUser
 * != null) { // Save logout time to the database
 * loggedInUser.setLastLogout(LocalDateTime.now());
 * userService.saveUser(loggedInUser); } session.invalidate(); return
 * "redirect:/"; }
 * 
 * @GetMapping("/userLogs") public ResponseEntity<List<User>> getUserLogs() {
 * System.out.println("3333333333333333333333333333333"); List<User> userssss =
 * userService.getAllUserLogs(); // Replace with your service method to fetch
 * user logs System.out.println("444444444444444444444444" + userssss); return
 * ResponseEntity.ok(userssss);
 * 
 * }
 * 
 * }
 */


package com.chatbotProject.chatbot.controller;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatbotProject.chatbot.entity.User;
import com.chatbotProject.chatbot.repo.UserRepo;
import com.chatbotProject.chatbot.service.UserService;
import com.chatbotProject.chatbot.service.TwoFactorAuthService;
import com.chatbotProject.chatbot.service.QRCodeService;

import org.jboss.aerogear.security.otp.Totp;
import com.google.zxing.WriterException;
import java.io.IOException;
import java.awt.image.BufferedImage;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Base64;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired(required = true)
    private UserRepo repo;

    @Autowired
    private TwoFactorAuthService twoFactorAuthService;

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/")
    public String login(Model model, @RequestParam(name = "error", required = false) String error) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("error", error);
        return "login";
    }

	@PostMapping("/userLogin")
    public String loginUser(@ModelAttribute("user") User user, Model model, HttpSession session) {
        String uname = user.getUname();
        Optional<User> userData = repo.findByUname(uname);
        if (userData.isPresent() && user.getPassword().equals(userData.get().getPassword())) {
            // Save login time to the database
            User loggedInUser = userData.get();
            loggedInUser.setLastLogin(LocalDateTime.now());
            userService.saveUser(loggedInUser);

            session.setAttribute("loggedInUser", loggedInUser);

            // Check if 2FA is enabled and redirect for OTP verification
            if (loggedInUser.getSecretKey() != null) {
                return "otpVerification";  // Show OTP verification page
            }

            // If no secret key exists, redirect to the 2FA setup page
            return "redirect:/setup2FA"; // Redirect to setup2FA
        } else {
            model.addAttribute("error", "Incorrect username or password.");
            return "login";
        }
    }
    
    @PostMapping("/verifyOTP")
	public String verifyOTP(@RequestParam("otp") String otp, HttpSession session, Model model) {
	    System.out.println("Verifying OTP...");

	    User loggedInUser = (User) session.getAttribute("loggedInUser");
	    if (loggedInUser != null && twoFactorAuthService.verifyOTP(loggedInUser.getSecretKey(), otp)) {
	        // OTP is correct, login successful

	        // After successful OTP verification, check if the user is an admin
	        String uname = loggedInUser.getUname();
	        boolean isAdmin = isAdminUser(uname);

	        if (isAdmin) {
	            model.addAttribute("isAdmin", true);
	        }

	        // Redirect to the home page
	        return "home";
	    } else {
	        model.addAttribute("error", "Invalid OTP.");
	        return "otpVerification";
	    }
	}

	@GetMapping("/setup2FA")
    public String setup2FA(HttpSession session, Model model) throws Exception {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            String secretKey = twoFactorAuthService.generateSecretKey();
            loggedInUser.setSecretKey(secretKey);
            userService.saveUser(loggedInUser);

            // Generate QR code for Google Authenticator
            BufferedImage qrCodeImage = twoFactorAuthService.generateQRCode(secretKey, loggedInUser.getUname());

            // Convert the QR code image to Base64 format
            String qrCodeBase64 = convertToBase64(qrCodeImage);

            // Pass the QR code and secret key to the frontend
            model.addAttribute("qrCode", qrCodeBase64);
            model.addAttribute("secret", secretKey);

            return "setup2fa";  // Show the QR code page
        }

        return "login";
    }

    // Helper method to convert BufferedImage to Base64
    private String convertToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    @GetMapping("/viewUsers")
    @ResponseBody
    public List<User> viewUsers(Model model) {
        List<User> users = userService.getAllUsers();
        System.out.println("Users saved: " + users);
        return users;
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        System.out.println("Received user: " + user);
        userService.saveUser(user);
        System.out.println("User saved: " + user);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            // Save logout time to the database
            loggedInUser.setLastLogout(LocalDateTime.now());
            userService.saveUser(loggedInUser);
        }
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/userLogs")
    public ResponseEntity<List<User>> getUserLogs() {
        System.out.println("3333333333333333333333333333333");
        List<User> userssss = userService.getAllUserLogs(); // Replace with your service method to fetch user logs
        System.out.println("444444444444444444444444" + userssss);
        return ResponseEntity.ok(userssss);
    }

    private boolean isAdminUser(String uname) {
        // Implement logic to check if the user is an admin
        // For example, check if the username is "admin"
        return "admin".equals(uname); // Replace "admin" with your admin username
    }
}
