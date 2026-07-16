package com.HonnaBot.SagaraMitra.controller;

import com.HonnaBot.SagaraMitra.Entity.User;
import com.HonnaBot.SagaraMitra.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {

        return userService.registerUser(user);
    }

    @GetMapping("/check-user")
    public Map<String, Boolean> checkUserExists(@RequestParam String userPhone) {
        boolean exists = userService.checkUserExists(userPhone);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }
    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody Map<String, String> loginRequest) {
        String userPhone = loginRequest.get("userPhone");
        String userPassword = loginRequest.get("userPassword");

        Map<String, String> response = new HashMap<>();
        String status = userService.validateUserLogin(userPhone, userPassword);
        response.put("status", status);
        return response;
    }
    @PostMapping("/logout")
    public String logoutUser() {
        userService.logoutUser();
        return "Logged out successfully!";
    }

    @GetMapping("/current-user")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

}