package com.ye13sh.controller;

import com.ye13sh.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
    Optional<Authority> findByUsername(String username);
    void deleteByUsername(String username);

    Authority getUserByUsername(String username);
//    @Query("SELECT a FROM authority a WHERE a.username = :username")
//    Optional<Authority> findByUser(@Param("username") String username);
}
