package com.revanth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revanth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String  email);

}