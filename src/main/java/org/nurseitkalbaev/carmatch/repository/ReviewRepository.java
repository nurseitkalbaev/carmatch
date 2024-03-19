package org.nurseitkalbaev.carmatch.repository;

import org.nurseitkalbaev.carmatch.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long userId);

    List<Review> findByCarId(Long carId);
}
