package com.cos.security1.repository;

import com.cos.security1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    // findBy 규칙 => Username 문법
    // select * from user wgere username
    public User findByUsername(String username);
}


