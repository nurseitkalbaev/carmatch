package org.nurseitkalbaev.carmatch.service;

import org.nurseitkalbaev.carmatch.model.User;
import java.util.List;

// UserService interface for managing user-related operations
public interface UserService {

    // Method to get all users
    List<User> getAllUsers();

    // Method to get user by ID
    User getUserById(Long userId);

    // Method to create a new user
    void createUser(User newUser);

    // Method to update an existing user
    void updateUser(Long userId, User updatedUser);
    void deleteUser(Long userId);
    User getUserByEmail(String email);
    User getUserProfile(Long userId);

    // Method to update user profile
    void updateUserProfile(Long userId, User updatedProfile);
    boolean authenticateUser(String email, String password);
}
