package org.nurseitkalbaev.carmatch.controller;

import jakarta.servlet.http.HttpSession;
import org.nurseitkalbaev.carmatch.model.User;
import org.nurseitkalbaev.carmatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        boolean isAuthenticated = session.getAttribute("user") != null;
        model.addAttribute("authenticated", isAuthenticated);
        return "index";
    }

    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        if (userService.authenticateUser(email, password)) {
            User user = userService.getUserByEmail(email);
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            return "redirect:/login?error";
        }
    }


    @GetMapping("/{userId}")
    public String getUserProfile(@PathVariable Long userId, Model model) {
        User user = userService.getUserProfile(userId);
        if (user == null) {
            return "error";
        }
        model.addAttribute("user", user);
        return "userProfile";
    }

    @PostMapping("/{userId}")
    public String updateUserProfile(@PathVariable Long userId, @ModelAttribute User updatedProfile) {
        User user = userService.updateUserProfile(userId, updatedProfile);
        if (user == null) {
            return "error";
        }
        return "/" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "/";
    }
}
