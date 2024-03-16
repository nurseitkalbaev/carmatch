package org.nurseitkalbaev.carmatch.service;

import org.nurseitkalbaev.carmatch.model.User;
import org.nurseitkalbaev.carmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
    public User createUser(User newUser){
        return userRepository.save(newUser);
    }
    public User updateUser(Long userId, User updatedUser){
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            updatedUser.setId(existingUser.getId());
            return userRepository.save(updatedUser);
        }
        return null;
    }
    public void deleteUser(Long userId){
        if (!userRepository.existsById(userId)) {
            return;
        }
        userRepository.deleteById(userId);
    }

}