package com.ye13sh.controller;

import com.ye13sh.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String username);

    void deleteByUsername(String username);
}
