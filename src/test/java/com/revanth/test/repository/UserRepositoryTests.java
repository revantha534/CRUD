package com.revanth.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.revanth.controller.UserController;
import com.revanth.model.User;
import com.revanth.repository.UserRepository;
import com.revanth.service.UserService;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");

        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<User> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");

        when(userService.getUserById(userId)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = List.of(
                new User(1L, "John", "Doe", "john.doe@example.com"),
                new User(2L, "Jane", "Doe", "jane.doe@example.com"));

        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        User existingUser = new User(userId, "John", "Doe", "john.doe@example.com");
        User updatedUser = new User(userId, "Updated John", "Updated Doe", "updated.john.doe@example.com");

        when(userService.updateUser(updatedUser)).thenReturn(updatedUser);
        when(userService.getUserById(userId)).thenReturn(existingUser);

        ResponseEntity<User> response = userController.updateUser(userId, updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        ResponseEntity<String> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User successfully deleted!", response.getBody());
        assertFalse(userRepository.existsById(userId));
    }
}
