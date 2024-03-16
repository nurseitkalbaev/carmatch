package org.nurseitkalbaev.carmatch.repository;

import org.nurseitkalbaev.carmatch.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingRepository extends JpaRepository <Booking, Long> {
}
