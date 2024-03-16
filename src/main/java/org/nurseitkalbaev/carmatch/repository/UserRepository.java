package org.nurseitkalbaev.carmatch.repository;

import org.nurseitkalbaev.carmatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}