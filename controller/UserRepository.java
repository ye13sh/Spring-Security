package com.ye13sh.controller;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ye13sh.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	void deleteByUsername(String username);

}
