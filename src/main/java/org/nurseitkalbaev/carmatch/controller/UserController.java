package org.nurseitkalbaev.carmatch.controller;

import org.nurseitkalbaev.carmatch.model.User;
import org.nurseitkalbaev.carmatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(User user) {
        userService.createUser(user);
        return "/login";
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
        return "/users/" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "/users";
    }
}
