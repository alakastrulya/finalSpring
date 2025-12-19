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
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest
public class SamuraiServiceTest {

    @Autowired
    private SamuraiService samuraiService;

    @Autowired
    private KatanaService katanaService;

    @Autowired
    private DojoService dojoService;

    @Test
    void getAll() {
        samuraiService.addSamurai(SamuraiDto.builder().nameDto("Testt").ageDto(55).build());

        List<SamuraiDto> list = samuraiService.getAll();
        assertNotNull(list);
        assertNotEquals(0, list.size());

        for (SamuraiDto samuraiDto : list) {
            assertNotNull(samuraiDto);
            assertNotNull(samuraiDto.getId());
            assertNotNull(samuraiDto.getNameDto());
            assertNotNull(samuraiDto.getAgeDto());

            List<DojoDto> dojos = samuraiDto.getDojos();
            if (dojos != null) {
                for (DojoDto dojoDto : dojos) {
                    assertNotNull(dojoDto);
                    assertNotNull(dojoDto.getId());
                }
            }
        }
    }

    @Test
    void getById() {
        Random random = new Random();
        int randomIndex = random.nextInt(samuraiService.getAll().size());
        Long id = samuraiService.getAll().get(randomIndex).getId();

        SamuraiDto samuraiDto = samuraiService.getById(id);
        assertNotNull(samuraiDto);
        assertNotNull(samuraiDto.getId());
        assertNotNull(samuraiDto.getNameDto());
        assertNotNull(samuraiDto.getAgeDto());

        List<DojoDto> dojos = samuraiDto.getDojos();
        if (dojos != null) {
            for (DojoDto dojoDto : dojos) {
                assertNotNull(dojoDto);
                assertNotNull(dojoDto.getId());
                assertNotNull(dojoDto.getNameDto());
            }
        }

        SamuraiDto notFound = samuraiService.getById(-1L);
        assertNull(notFound);
    }

    @Test
    void add() {
        DojoDto dojo1 = DojoDto.builder().nameDto("Osaka").build();
        DojoDto savedDojo1 = dojoService.addDojo(dojo1);

        DojoDto dojo2 = DojoDto.builder().nameDto("Edo").build();
        DojoDto savedDojo2 = dojoService.addDojo(dojo2);

        List<DojoDto> dojos = new ArrayList<>();
        dojos.add(savedDojo1);
        dojos.add(savedDojo2);

        SamuraiDto samuraiDto = SamuraiDto.builder()
                .nameDto("Samurai")
                .ageDto(33)
                .dojos(dojos)
                .build();

        SamuraiDto created = samuraiService.addSamurai(samuraiDto);
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals(samuraiDto.getNameDto(), created.getNameDto());
        assertEquals(samuraiDto.getAgeDto(), created.getAgeDto());

        List<DojoDto> createdDojos = created.getDojos();
        assertNotNull(createdDojos);
        assertNotEquals(0, createdDojos.size());

        SamuraiDto check = samuraiService.getById(created.getId());
        assertNotNull(check);
        assertEquals(samuraiDto.getNameDto(), check.getNameDto());

        List<DojoDto> checkDojos = check.getDojos();
        assertNotNull(checkDojos);
        assertNotEquals(0, checkDojos.size());
    }

    @Test
    void update() {
        Random random = new Random();
        int randomIndex = random.nextInt(samuraiService.getAll().size());
        Long id = samuraiService.getAll().get(randomIndex).getId();

        SamuraiDto updateDto = SamuraiDto.builder()
                .nameDto("Legenda")
                .ageDto(50)
                .build();

        SamuraiDto updated = samuraiService.updateSamurai(id, updateDto);
        assertNotNull(updated);
        assertEquals(updateDto.getNameDto(), updated.getNameDto());
        assertEquals(updateDto.getAgeDto(), updated.getAgeDto());

        SamuraiDto check = samuraiService.getById(id);
        assertEquals(updateDto.getNameDto(), check.getNameDto());
        assertEquals(updateDto.getAgeDto(), check.getAgeDto());
    }

    @Test
    void delete() {
        Random random = new Random();
        int randomIndex = random.nextInt(samuraiService.getAll().size());
        Long id = samuraiService.getAll().get(randomIndex).getId();

        for (KatanaDto katana : katanaService.getAll()) {
            if (katana.getSamuraiDto() != null && katana.getSamuraiDto().getId().equals(id)) {
                katanaService.deleteKatana(katana.getId());
            }
        }

        assertTrue(samuraiService.deleteSamurai(id));

        SamuraiDto check = samuraiService.getById(id);
        assertNull(check);
    }
}