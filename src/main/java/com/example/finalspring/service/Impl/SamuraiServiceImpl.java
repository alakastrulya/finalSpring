package com.example.finalspring.service.Impl;

import com.example.finalspring.dto.DojoDto;
import com.example.finalspring.dto.SamuraiDto;
import com.example.finalspring.entity.Dojo;
import com.example.finalspring.entity.Samurai;
import com.example.finalspring.mapper.SamuraiMapper;
import com.example.finalspring.repository.DojoRepository;
import com.example.finalspring.repository.SamuraiRepository;
import com.example.finalspring.service.SamuraiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class SamuraiServiceImpl implements SamuraiService {
    private final SamuraiRepository samuraiRepository;
    private final SamuraiMapper samuraiMapper;
    private final DojoRepository dojoRepository;

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
        Samurai samurai = samuraiMapper.toEntity(samuraiDto);

        samurai.setName(samuraiDto.getNameDto());
        samurai.setAge(samuraiDto.getAgeDto());

        setDojosFromDto(samurai, samuraiDto);

        Samurai saved = samuraiRepository.save(samurai);
        return samuraiMapper.toDto(saved);
    }

    @Override
    public SamuraiDto updateSamurai(Long id, SamuraiDto samuraiDto) {
        Samurai samurai = samuraiRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Samurai with id " + id + " not found"));

        samurai.setName(samuraiDto.getNameDto());
        samurai.setAge(samuraiDto.getAgeDto());

        setDojosFromDto(samurai, samuraiDto);

        return samuraiMapper.toDto(samuraiRepository.save(samurai));
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

    private void setDojosFromDto(Samurai samurai, SamuraiDto samuraiDto) {
        if (samuraiDto.getDojos() != null && !samuraiDto.getDojos().isEmpty()) {
            List<Dojo> dojos = new ArrayList<>();
            for (DojoDto dojoDto : samuraiDto.getDojos()) {
                if (dojoDto.getId() != null) {
                    Dojo dojo = dojoRepository.findById(dojoDto.getId()).orElse(null);
                    if (dojo != null) {
                        dojos.add(dojo);
                    }
                }
            }
            samurai.setDojos(dojos);
        } else {
            samurai.setDojos(null);
        }
    }
}
