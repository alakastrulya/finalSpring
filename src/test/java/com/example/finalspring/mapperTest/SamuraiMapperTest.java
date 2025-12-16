package com.example.finalspring.mapperTest;

import com.example.finalspring.dto.DojoDto;
import com.example.finalspring.dto.SamuraiDto;
import com.example.finalspring.entity.Dojo;
import com.example.finalspring.entity.Samurai;
import com.example.finalspring.mapper.SamuraiMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SamuraiMapperTest {
    @Autowired
    private SamuraiMapper samuraiMapper;
    @Test
    void convertEntityToDto(){
        Dojo dojo1 = new Dojo();
        dojo1.setId(1L);
        dojo1.setName("Dojo One");

        Dojo dojo2 = new Dojo();
        dojo2.setId(2L);
        dojo2.setName("Dojo Two");

        List<Dojo> dojoList = Arrays.asList(dojo1, dojo2);

        Samurai samurai = new Samurai();
        samurai.setId(100L);
        samurai.setName("Hattori");
        samurai.setAge(30);
        samurai.setDojos(dojoList);

        SamuraiDto samuraiDto = samuraiMapper.toDto(samurai);
        Assertions.assertNotNull(samuraiDto);
        Assertions.assertNotNull(samuraiDto.getId());
        Assertions.assertNotNull(samuraiDto.getNameDto());
        Assertions.assertNotNull(samuraiDto.getAgeDto());
        Assertions.assertNotNull(samuraiDto.getDojos());

        Assertions.assertEquals(samurai.getId(), samuraiDto.getId());
        Assertions.assertEquals(samurai.getName(), samuraiDto.getNameDto());
        Assertions.assertEquals(samurai.getAge(), samuraiDto.getAgeDto());
        Assertions.assertEquals(samurai.getDojos().size(), samuraiDto.getDojos().size());
    }

    @Test
    void convertDtoToEntity(){
        DojoDto dojoDto1 = new DojoDto();
        dojoDto1.setId(1L);
        dojoDto1.setNameDto("Dojo One");

        DojoDto dojoDto2 = new DojoDto();
        dojoDto2.setId(2L);
        dojoDto2.setNameDto("Dojo Two");

        List<DojoDto> dojoDtoList = Arrays.asList(dojoDto1, dojoDto2);

        SamuraiDto samuraiDto = new SamuraiDto();
        samuraiDto.setId(100L);
        samuraiDto.setNameDto("Hattori");
        samuraiDto.setAgeDto(30);
        samuraiDto.setDojos(dojoDtoList);

        Samurai samurai = samuraiMapper.toEntity(samuraiDto);
        Assertions.assertNotNull(samurai);
        Assertions.assertNotNull(samurai.getId());
        Assertions.assertNotNull(samurai.getName());
        Assertions.assertNotNull(samurai.getAge());
        Assertions.assertNotNull(samurai.getDojos());

        Assertions.assertEquals(samuraiDto.getId(), samurai.getId());
        Assertions.assertEquals(samuraiDto.getNameDto(), samurai.getName());
        Assertions.assertEquals(samuraiDto.getAgeDto(), samurai.getAge());
        Assertions.assertEquals(samuraiDto.getDojos().size(), samurai.getDojos().size());

    }

    @Test
    void convertEntityListToDtoList() {
        Samurai samurai1 = new Samurai();
        samurai1.setId(1L);
        samurai1.setName("Samurai One");
        samurai1.setAge(25);
        samurai1.setDojos(new ArrayList<>());

        Samurai samurai2 = new Samurai();
        samurai2.setId(2L);
        samurai2.setName("Samurai Two");
        samurai2.setAge(35);
        samurai2.setDojos(new ArrayList<>());

        List<Samurai> entityList = new ArrayList<>();
        entityList.add(samurai1);
        entityList.add(samurai2);

        List<SamuraiDto> dtoList = samuraiMapper.toDtoList(entityList);
        Assertions.assertNotNull(dtoList);
        Assertions.assertNotEquals(0, dtoList.size());
        Assertions.assertEquals(entityList.size(), dtoList.size());

        for (int i = 0; i < dtoList.size(); i++) {
            Samurai entity = entityList.get(i);
            SamuraiDto dto = dtoList.get(i);

            Assertions.assertNotNull(entity);
            Assertions.assertNotNull(dto);

            Assertions.assertEquals(entity.getId(), dto.getId());
            Assertions.assertEquals(entity.getName(), dto.getNameDto());
            Assertions.assertEquals(entity.getAge(), dto.getAgeDto());
            Assertions.assertEquals(entity.getDojos().size(), dto.getDojos().size());

            for (int j = 0; j < dto.getDojos().size(); j++) {
                Dojo dojoEntity = entity.getDojos().get(j);
                DojoDto dojoDto = dto.getDojos().get(j);
                Assertions.assertEquals(dojoEntity.getId(), dojoDto.getId());
            }
        }
    }
}






