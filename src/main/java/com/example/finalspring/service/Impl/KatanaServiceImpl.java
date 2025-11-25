package com.example.finalspring.service.Impl;

import com.example.finalspring.dto.KatanaDto;
import com.example.finalspring.entity.Katana;
import com.example.finalspring.entity.Samurai;
import com.example.finalspring.mapper.KatanaMapper;
import com.example.finalspring.mapper.SamuraiMapper;
import com.example.finalspring.repository.KatanaRepository;
import com.example.finalspring.repository.SamuraiRepository;
import com.example.finalspring.service.KatanaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service

public class KatanaServiceImpl implements KatanaService {
    private final KatanaRepository katanaRepository;
    private final KatanaMapper katanaMapper;
    private final SamuraiMapper samuraiMapper;
    private final SamuraiRepository samuraiRepository;
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
        KatanaDto savekatanaDto = new KatanaDto();
        savekatanaDto.setAgeDto(katanaDto.getAgeDto());
        savekatanaDto.setNameDto(katanaDto.getNameDto());
        savekatanaDto.setBySwordsmanDto(katanaDto.getBySwordsmanDto());

            if (samuraiRepository.findById(katanaDto.getSamuraiDto().getId()).orElse(null) == null) {
                Samurai samurai = new Samurai();
                samurai.setName(katanaDto.getSamuraiDto().getNameDto());
                samurai.setAge(katanaDto.getSamuraiDto().getAgeDto());
                Samurai samurai1 = samuraiRepository.save(samurai);
                savekatanaDto.setSamuraiDto(samuraiMapper.toDto(samurai1));

            }else{
                savekatanaDto.setSamuraiDto(katanaDto.getSamuraiDto());
            }



        return katanaMapper.toDto(katanaRepository.save(katanaMapper.toEntity(savekatanaDto)));
    }

    @Override
    public KatanaDto updateKatana(Long id, KatanaDto katanaDto) {

        Katana katana = katanaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Katana not found"));

        katana.setName(katanaDto.getNameDto());
        katana.setAge(katanaDto.getAgeDto());
        katana.setBySwordsman(katanaDto.getBySwordsmanDto());


        Long samuraiId = katanaDto.getSamuraiDto().getId();
        Samurai samurai;
        if (samuraiId != null && samuraiRepository.existsById(samuraiId)) {
            samurai = samuraiRepository.findById(samuraiId).get();
            samurai.setName(katanaDto.getSamuraiDto().getNameDto());
            samurai.setAge(katanaDto.getSamuraiDto().getAgeDto());
            samurai = samuraiRepository.save(samurai);
        } else {
            samurai = new Samurai();
            samurai.setName(katanaDto.getSamuraiDto().getNameDto());
            samurai.setAge(katanaDto.getSamuraiDto().getAgeDto());
            samurai = samuraiRepository.save(samurai);
        }

        katana.setSamurai(samurai);

        Katana savedKatana = katanaRepository.save(katana);

        return katanaMapper.toDto(savedKatana);
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
