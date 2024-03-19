package org.nurseitkalbaev.carmatch.controller;

import org.nurseitkalbaev.carmatch.model.Review;
import org.nurseitkalbaev.carmatch.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/{bookingId}")
    public String addReview(@PathVariable Long bookingId, @ModelAttribute Review review) {
        reviewService.addReview(bookingId, review);
        return "/bookings";
    }

    @GetMapping("/car/{carId}")
    public String getReviewsForCar(@PathVariable Long carId, Model model) {
        List<Review> reviews = reviewService.getReviewsForCar(carId);
        model.addAttribute("reviews", reviews);
        return "carReviews";
    }

    @GetMapping("/user/{userId}")
    public String getReviewsForUser(@PathVariable Long userId, Model model) {
        List<Review> reviews = reviewService.getReviewsForUser(userId);
        model.addAttribute("reviews", reviews);
        return "userReviews";
    }

}
