package org.nurseitkalbaev.carmatch.repository;

import org.nurseitkalbaev.carmatch.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
