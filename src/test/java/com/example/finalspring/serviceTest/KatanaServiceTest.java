package com.example.finalspring.serviceTest;

import com.example.finalspring.dto.DojoDto;
import com.example.finalspring.dto.KatanaDto;
import com.example.finalspring.dto.SamuraiDto;
import com.example.finalspring.service.DojoService;
import com.example.finalspring.service.KatanaService;
import com.example.finalspring.service.SamuraiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class KatanaServiceTest {
    @Autowired
    private KatanaService katanaService;

    @Autowired
    private SamuraiService samuraiService;

    @Test
    void getAll() {
        SamuraiDto samuraiDto = SamuraiDto.builder()
                .nameDto("Test")
                .ageDto(30)
                .build();
        SamuraiDto savedSamurai = samuraiService.addSamurai(samuraiDto);

        KatanaDto katanaDto = KatanaDto.builder()
                .nameDto("Test")
                .ageDto(100)
                .bySwordsmanDto("Master")
                .samuraiDto(savedSamurai)
                .build();

        katanaService.addKatana(katanaDto);

        List<KatanaDto> list = katanaService.getAll();
        assertNotNull(list);
        assertNotEquals(0, list.size());

        for (KatanaDto dto : list) {
            assertNotNull(dto);
            assertNotNull(dto.getId());
            assertNotNull(dto.getNameDto());
            assertNotNull(dto.getAgeDto());
            assertNotNull(dto.getBySwordsmanDto());
            assertNotNull(dto.getSamuraiDto());
            assertNotNull(dto.getSamuraiDto().getNameDto());
            assertNotNull(dto.getSamuraiDto().getAgeDto());
        }
    }

    @Test
    void getById() {
        Random random = new Random();
        int randomIndex = random.nextInt(katanaService.getAll().size());
        Long id = katanaService.getAll().get(randomIndex).getId();

        KatanaDto dto = katanaService.getById(id);
        assertNotNull(dto);
        assertNotNull(dto.getId());
        assertNotNull(dto.getNameDto());
        assertNotNull(dto.getAgeDto());
        assertNotNull(dto.getBySwordsmanDto());
        assertNotNull(dto.getSamuraiDto());

        KatanaDto notFound = katanaService.getById(-1L);
        assertNull(notFound);
    }

    @Test
    void add() {
        SamuraiDto samuraiDto = SamuraiDto.builder()
                .nameDto("Yo")
                .ageDto(38)
                .build();
        SamuraiDto savedSamurai = samuraiService.addSamurai(samuraiDto);

        KatanaDto katanaDto = KatanaDto.builder()
                .nameDto("Makeba")
                .ageDto(150)
                .bySwordsmanDto("Swordsman")
                .samuraiDto(savedSamurai)
                .build();

        KatanaDto created = katanaService.addKatana(katanaDto);
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals(katanaDto.getNameDto(), created.getNameDto());
        assertEquals(katanaDto.getAgeDto(), created.getAgeDto());
        assertEquals(katanaDto.getBySwordsmanDto(), created.getBySwordsmanDto());
        assertNotNull(created.getSamuraiDto());
        assertEquals(savedSamurai.getId(), created.getSamuraiDto().getId());

        KatanaDto check = katanaService.getById(created.getId());
        assertEquals(katanaDto.getNameDto(), check.getNameDto());
        assertEquals(savedSamurai.getNameDto(), check.getSamuraiDto().getNameDto());
    }

    @Test
    void update() {
        Random random = new Random();
        int randomIndex = random.nextInt(katanaService.getAll().size());
        Long id = katanaService.getAll().get(randomIndex).getId();

        SamuraiDto newSamurai = SamuraiDto.builder()
                .nameDto("New")
                .ageDto(50)
                .build();
        SamuraiDto savedSamurai = samuraiService.addSamurai(newSamurai);

        KatanaDto updateDto = KatanaDto.builder()
                .nameDto("Updated")
                .ageDto(200)
                .bySwordsmanDto("Baska")
                .samuraiDto(savedSamurai)
                .build();

        KatanaDto updated = katanaService.updateKatana(id, updateDto);
        assertNotNull(updated);
        assertEquals(updateDto.getNameDto(), updated.getNameDto());
        assertEquals(200, updated.getAgeDto());
        assertEquals(updateDto.getBySwordsmanDto(), updated.getBySwordsmanDto());
        assertEquals(savedSamurai.getId(), updated.getSamuraiDto().getId());

        KatanaDto check = katanaService.getById(id);
        assertEquals(updateDto.getNameDto(), check.getNameDto());
        assertEquals(savedSamurai.getNameDto(), check.getSamuraiDto().getNameDto());
    }

    @Test
    void delete() {
        Random random = new Random();
        int randomIndex = random.nextInt(katanaService.getAll().size());
        Long id = katanaService.getAll().get(randomIndex).getId();

        assertTrue(katanaService.deleteKatana(id));

        KatanaDto check = katanaService.getById(id);
        assertNull(check);
    }
}
