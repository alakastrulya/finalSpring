package com.example.finalspring.service;
import com.example.finalspring.dto.SamuraiDto;
import java.util.List;

public interface SamuraiService {
    List<SamuraiDto> getAll();
    SamuraiDto getById( Long id);
    SamuraiDto addSamurai(SamuraiDto samuraiDto);
    SamuraiDto updateSamurai(Long id, SamuraiDto samuraiDto);
    boolean deleteSamurai(Long id);
}
