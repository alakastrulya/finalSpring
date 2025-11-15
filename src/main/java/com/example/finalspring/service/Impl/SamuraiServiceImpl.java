package com.example.finalspring.service.Impl;

import com.example.finalspring.dto.SamuraiDto;
import com.example.finalspring.entity.Samurai;
import com.example.finalspring.mapper.SamuraiMapper;
import com.example.finalspring.repository.SamuraiRepository;
import com.example.finalspring.service.SamuraiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class SamuraiServiceImpl implements SamuraiService {
    private final SamuraiRepository samuraiRepository;
    private final SamuraiMapper samuraiMapper;
    @Override
    public List<SamuraiDto> getAll() {
        return samuraiMapper.toDtoList(samuraiRepository.findAll());
    }

    @Override
    public SamuraiDto getById(Long id) {
        return samuraiMapper.toDto(samuraiRepository.findById(id).orElse(null));
    }

    @Override
    public SamuraiDto addSamurai(SamuraiDto samuraiDto) {
        return samuraiMapper.toDto(samuraiRepository.save(samuraiMapper.toEntity(samuraiDto)));
    }

    @Override
    public SamuraiDto updateSamurai(Long id, SamuraiDto samuraiDto) {
        Samurai samurai = samuraiRepository.findById(id).orElse(null);
        if(!Objects.isNull(samurai)){
            samurai.setName(samuraiDto.getNameDto());
            samurai.setAge(samuraiDto.getAgeDto());
            return samuraiMapper.toDto(samuraiRepository.save(samurai));
        }
        throw new RuntimeException("Samurai with id " + id + " not found");
    }

    @Override
    public boolean deleteSamurai(Long id) {
        Samurai samurai = samuraiRepository.findById(id).orElse(null);
        if (samurai == null){
            return false;
        }
        samuraiRepository.delete(samurai);
        return true;
    }
}
