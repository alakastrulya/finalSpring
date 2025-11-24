package com.example.finalspring.service;
import com.example.finalspring.dto.DojoDto;
import java.util.List;

public interface DojoService {
 List<DojoDto> getAll();
 DojoDto getById(Long id);
 DojoDto addDojo(DojoDto dojoDto);
 DojoDto updateDojo(Long id, DojoDto dojoDto);
 boolean deleteDojo(Long id);
}
