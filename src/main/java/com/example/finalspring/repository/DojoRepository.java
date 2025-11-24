package com.example.finalspring.repository;

import com.example.finalspring.entity.Dojo;
import com.example.finalspring.entity.Samurai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DojoRepository extends JpaRepository<Dojo,Long> {
}
