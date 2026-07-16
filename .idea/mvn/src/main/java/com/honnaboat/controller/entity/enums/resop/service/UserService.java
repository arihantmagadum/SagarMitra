package com.HonnaBot.SagaraMitra.Service;

import com.HonnaBot.SagaraMitra.Entity.User;
import com.HonnaBot.SagaraMitra.Repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession session;

    public String registerUser(User user) {
        if (userRepository.existsByUserPhone(user.getUserPhone())) {
            return "Phone number already registered!";
        }
        userRepository.save(user);
        return "User registered successfully!";
    }

    public boolean checkUserExists(String userPhone) {
        return userRepository.existsByUserPhone(userPhone);
    }
    public String validateUserLogin(String userPhone, String userPassword) {
        User user = userRepository.findByUserPhone(userPhone);
        if (user == null) {
            return "not_found";
        }
        if (!user.getUserPassword().equals(userPassword)) {
            return "wrong_password";
        }
        session.setAttribute("user", user);

        return "success";
    }
    public void logoutUser() {
        session.invalidate(); // Clear session
    }

    public User getCurrentUser() {
        return (User) session.getAttribute("user");
    }
}