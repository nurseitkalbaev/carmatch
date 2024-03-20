package org.nurseitkalbaev.carmatch.service;

import org.nurseitkalbaev.carmatch.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User createUser(User newUser);
    User updateUser(Long userId, User updatedUser);
    void deleteUser(Long userId);
    User getUserByEmail(String email);
    User getUserProfile(Long userId);
    User updateUserProfile(Long userId, User updatedProfile);
    boolean authenticateUser(String email, String password);
}
