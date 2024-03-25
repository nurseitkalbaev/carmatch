package org.nurseitkalbaev.carmatch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.nurseitkalbaev.carmatch.model.User;
import org.nurseitkalbaev.carmatch.repository.UserRepository;
import org.nurseitkalbaev.carmatch.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<User> expectedUsers = List.of(new User(), new User());
        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.getAllUsers();

        assertEquals(expectedUsers.size(), actualUsers.size());
        assertEquals(expectedUsers.get(0), actualUsers.get(0));
        assertEquals(expectedUsers.get(1), actualUsers.get(1));
    }

    @Test
    public void testGetUserById() {

        Long userId = 1L;
        User expectedUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.getUserById(userId);

        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testCreateUser() {

        User newUser = new User();

        when(userRepository.save(newUser)).thenReturn(newUser);

        userService.createUser(newUser);

        verify(userRepository, times(1)).save(newUser);
    }

}
