package org.nurseitkalbaev.carmatch.service;

import org.nurseitkalbaev.carmatch.model.Review;
import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    Review getReviewById(Long id);
    Review createReview(Review newReview);
    Review updateReview(Long id, Review updatedReview);
    void deleteReview(Long id);
    List<Review> getReviewsByUserId(Long userId);
    void addReview(Long bookingId, Review review);
    List<Review> getReviewsForCar(Long carId);
    List<Review> getReviewsForUser(Long userId);
}
