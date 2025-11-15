package com.example.finalspring.service.Impl;

import com.example.finalspring.dto.KatanaDto;
import com.example.finalspring.entity.Katana;
import com.example.finalspring.mapper.KatanaMapper;
import com.example.finalspring.repository.KatanaRepository;
import com.example.finalspring.service.KatanaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service

public class KatanaServiceImpl implements KatanaService {
    private final KatanaRepository katanaRepository;
    private final KatanaMapper katanaMapper;
    @Override
    public List<KatanaDto> getAll() {
        return katanaMapper.toDtoList(katanaRepository.findAll());
    }

    @Override
    public KatanaDto getById(Long id) {
        return katanaMapper.toDto(katanaRepository.findById(id).orElse(null));
    }

    @Override
    public KatanaDto addKatana(KatanaDto katanaDto) {
        return katanaMapper.toDto(katanaRepository.save(katanaMapper.toEntity(katanaDto)));
    }

    @Override
    public KatanaDto updateKatana(Long id, KatanaDto katanaDto) {
        Katana katana = katanaRepository.findById(id).orElse(null);
        katana.setId(1L);
        katana.setAge(25);
        katana.setName("Takeda Shingen");
        katana.setBySwordsman("Katana Master");
        katanaRepository.save(katana);

        Katana katana1 = katanaRepository.findById(id).orElse(null);
        return katanaMapper.toDto(katana1);
    }

    @Override
    public boolean deleteKatana(Long id) {
        Katana katana = katanaRepository.findById(id).orElse(null);
        if (katana == null){
            return false;
        }
        katanaRepository.delete(katana);
        return true;
    }
}
