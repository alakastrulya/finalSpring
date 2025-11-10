package com.example.finalspring.repository;

import com.example.finalspring.dto.KatanaDto;
import com.example.finalspring.dto.SamuraiDto;
import com.example.finalspring.entity.Katana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KatanaRepository extends JpaRepository<Katana,Long> {
}
