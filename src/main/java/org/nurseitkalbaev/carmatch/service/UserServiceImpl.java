package org.nurseitkalbaev.carmatch.service;

import org.nurseitkalbaev.carmatch.model.User;
import org.nurseitkalbaev.carmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
    public void createUser(User newUser){
        userRepository.save(newUser);
    }
    public void updateUser(Long userId, User updatedUser){
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            updatedUser.setId(existingUser.getId());
            userRepository.save(updatedUser);
        }
    }
    public void deleteUser(Long userId){
        if (!userRepository.existsById(userId)) {
            return;
        }
        userRepository.deleteById(userId);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserProfile(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void updateUserProfile(Long userId, User updatedProfile) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {

            // Merge data from updatedProfile into existingUser
            existingUser.setFirstName(updatedProfile.getFirstName());
            existingUser.setLastName(updatedProfile.getLastName());
            existingUser.setEmail(updatedProfile.getEmail());
            existingUser.setPhone(updatedProfile.getPhone());
            existingUser.setAddress(updatedProfile.getAddress());
            existingUser.setCity(updatedProfile.getCity());
            existingUser.setState(updatedProfile.getState());
            existingUser.setDateOfBirth(updatedProfile.getDateOfBirth());


            userRepository.save(existingUser);
        }
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

}