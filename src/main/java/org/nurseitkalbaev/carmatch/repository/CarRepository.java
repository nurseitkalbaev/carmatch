package org.nurseitkalbaev.carmatch.repository;

import org.nurseitkalbaev.carmatch.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}