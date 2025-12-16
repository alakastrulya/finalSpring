package com.example.finalspring.repository;

import com.example.finalspring.dto.SamuraiDto;
import com.example.finalspring.entity.Dojo;
import com.example.finalspring.entity.Samurai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SamuraiRepository extends JpaRepository<Samurai,Long> {
    List<Samurai> findAllByDojosContaining(Dojo dojo);
}
