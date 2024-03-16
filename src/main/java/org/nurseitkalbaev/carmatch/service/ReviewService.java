package org.nurseitkalbaev.carmatch.service;

import org.nurseitkalbaev.carmatch.model.Review;
import org.nurseitkalbaev.carmatch.repository.ReviewRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public Review createReview(Review newReview) {
        return reviewRepository.save(newReview);
    }

    public Review updateReview(Long id, Review updatedReview) {
        if (updatedReview == null) {
            return null;
        }

        Review review = reviewRepository.findById(id).orElse(null);

        if (review != null) {
            BeanUtils.copyProperties(updatedReview, review, "id");

            return reviewRepository.save(review);
        } else {
            return null;
        }
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}