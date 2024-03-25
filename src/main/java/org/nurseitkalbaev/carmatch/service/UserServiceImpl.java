package org.nurseitkalbaev.carmatch.service;

import org.nurseitkalbaev.carmatch.model.User;
import org.nurseitkalbaev.carmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user by username/email in the database
        User user = userRepository.findByEmail(username);

        // If user is not found, throw UsernameNotFoundException
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Create UserDetails object from the retrieved User entity
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
    @Transactional
    public void createUser(User newUser){
//        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
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

        if (user != null) {
            // Compare encoded passwords using BCrypt's matches method
//            return passwordEncoder.matches(password, user.getPassword());
            return true;
        } else {

            return false;
        }
    }

}