package com.example.finalspring.repository;

import com.example.finalspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRep extends JpaRepository<User, Long>{
    User findByLogin(String login);
}

