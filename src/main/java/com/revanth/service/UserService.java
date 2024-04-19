package com.revanth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revanth.model.User;
import com.revanth.repository.UserRepository;



@Service
public class UserService  {

	@Autowired
    UserRepository userRepository;
	
	
	 public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }
	
	 public User getUserById(Long userId) {
	        Optional<User> optionalUser = userRepository.findById(userId);
	        return optionalUser.get();
	 }


    public User createUser(User user) {
        return userRepository.save(user);
    }

   
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}