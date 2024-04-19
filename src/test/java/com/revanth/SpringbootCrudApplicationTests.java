package com.revanth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.revanth.model.User;
import com.revanth.repository.UserRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class SpringbootCrudApplicationTests {

	  @Autowired
	  UserRepository userRepository;
	
	  @Test
	  @Order(1)
	  @Rollback(value = false)
	 public void testCreate() {
		  User user = new User();
		  user.setId(1L);
		  user.setFirstName("Ravindra");
		  user.setLastName("jadeja");
		  user.setEmail("jaddu@bcci.com");
		  userRepository.save(user);
		  assertNotNull(userRepository.findById(1L).get());
		  
	}
	  @Test
	  @Order(2)
	  @Rollback(value = false)
	  public void testReadALL() {
		  List<User> users = userRepository.findAll();
		  assertThat(users).size().isGreaterThan(0);
	  }
	  
	  @Test
		@Order(3)
	  @Rollback(value = false)
		public void testRead () {
		  User user=userRepository.findById(1L).get();
		  assertEquals("Ravindra", user.getFirstName());
	  }
	  
	  @Test
	  @Order(4)
	  @Rollback(value = false)
		public void testUpdate () {
		  
		 User user=userRepository.findById(1L).get();
		 user.setEmail("jadducsk@gmail.com");
		 userRepository.save(user);
		 assertNotEquals("jaddu@bcci.com", userRepository.findById(1L).get().getEmail());
	  }
	  @Test
		@Order(5)
	  @Rollback(value = false)
		public void testDelete() {
		  userRepository.deleteById(1L);
		  assertThat(userRepository.existsById(1L)).isFalse();
	  }

}
