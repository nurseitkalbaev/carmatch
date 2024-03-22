package org.nurseitkalbaev.carmatch.controller;

import jakarta.servlet.http.HttpSession;
import org.nurseitkalbaev.carmatch.model.Review;
import org.nurseitkalbaev.carmatch.model.User;
import org.nurseitkalbaev.carmatch.service.ReviewService;
import org.nurseitkalbaev.carmatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;


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
            session.setAttribute("loggedIn", true);
            return "redirect:/";
        } else {
            return "redirect:/login?error";
        }
    }


    @GetMapping("/profile")
    public String getUserProfile( Model model , HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean isAuthenticated = session.getAttribute("user") != null;
        model.addAttribute("authenticated", isAuthenticated);
        if (user == null) {
            return "redirect:/login";
        }
        List<Review> userReviews = reviewService.getReviewsByUserId((user.getId()));
        model.addAttribute("user", user);
        model.addAttribute("reviews", userReviews);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute User updatedProfile, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (!currentUser.getEmail().equals(updatedProfile.getEmail())) {
            User existingUserWithEmail = userService.getUserByEmail(updatedProfile.getEmail());
            if (existingUserWithEmail != null && existingUserWithEmail.getId() != currentUser.getId()) {
                return "redirect:/profile?error=email_conflict";
            }
        }
        userService.updateUserProfile(currentUser.getId(), updatedProfile);
        return "/profile";
    }


    @PostMapping("/profile/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 HttpSession session,
                                 Model model) {
        User currentUser = (User) session.getAttribute("user");
        // Check if the old password matches the current user's password
        if (!oldPassword.equals(currentUser.getPassword())) {
            model.addAttribute("error", "Incorrect old password");
            return "redirect:/profile";
        }
        // Check if the new password and confirm password match
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "New password and confirm password do not match");
            return "redirect:/profile";
        }
        // Update the user's password with the new password
        currentUser.setPassword(newPassword);
        userService.updateUser(currentUser.getId(), currentUser); // Assuming you have a method to update the user in your service

        return "redirect:/profile";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    @PostMapping("/delete-account")
    public String deleteAccount(@RequestParam String password, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null && userService.authenticateUser(user.getEmail(), password)) {
            userService.deleteUser(user.getId());
            session.invalidate();
            return "redirect:/login";
        } else {
            return "redirect:/profile?error=password";
        }
    }

}
