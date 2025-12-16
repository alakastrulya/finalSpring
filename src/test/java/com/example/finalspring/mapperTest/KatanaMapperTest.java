package com.example.finalspring.mapperTest;
import com.example.finalspring.dto.DojoDto;
import com.example.finalspring.dto.KatanaDto;
import com.example.finalspring.dto.SamuraiDto;
import com.example.finalspring.entity.Dojo;
import com.example.finalspring.entity.Katana;
import com.example.finalspring.entity.Samurai;
import com.example.finalspring.mapper.KatanaMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class KatanaMapperTest {
    @Autowired
    private KatanaMapper katanaMapper;

    @Test
    void convertEntityToDto() {
        Samurai samurai = new Samurai();
        samurai.setId(1L);
        samurai.setName("Naruto");
        samurai.setAge(19);

        Katana katana = new Katana();
        katana.setId(10L);
        katana.setName("Kusanagi");
        katana.setAge(10);
        katana.setBySwordsman("Naruto");
        katana.setSamurai(samurai);

        KatanaDto katanaDto = katanaMapper.toDto(katana);
        Assertions.assertNotNull(katanaDto);
        Assertions.assertNotNull(katanaDto.getId());
        Assertions.assertNotNull(katanaDto.getNameDto());
        Assertions.assertNotNull(katanaDto.getAgeDto());
        Assertions.assertNotNull(katanaDto.getBySwordsmanDto());
        Assertions.assertNotNull(katanaDto.getSamuraiDto());

        Assertions.assertEquals(katana.getId(), katanaDto.getId());
        Assertions.assertEquals(katana.getName(), katanaDto.getNameDto());
        Assertions.assertEquals(katana.getAge(), katanaDto.getAgeDto());
        Assertions.assertEquals(katana.getBySwordsman(), katanaDto.getBySwordsmanDto());
        Assertions.assertEquals(samurai.getId(), katanaDto.getSamuraiDto().getId());
        Assertions.assertEquals(samurai.getName(), katanaDto.getSamuraiDto().getNameDto());
        Assertions.assertEquals(samurai.getAge(), katanaDto.getSamuraiDto().getAgeDto());
    }

    @Test
    void convertDtoToEntity() {
        SamuraiDto samuraiDto = new SamuraiDto();
        samuraiDto.setId(1L);
        samuraiDto.setNameDto("Naruto");
        samuraiDto.setAgeDto(19);

        KatanaDto katanaDto = new KatanaDto();
        katanaDto.setId(1L);
        katanaDto.setNameDto("Kunnai");
        katanaDto.setAgeDto(55);
        katanaDto.setBySwordsmanDto("Kakashi");
        katanaDto.setSamuraiDto(samuraiDto);

        Katana katana = katanaMapper.toEntity(katanaDto);
        Assertions.assertNotNull(katana);
        Assertions.assertNotNull(katana.getId());
        Assertions.assertNotNull(katana.getAge());
        Assertions.assertNotNull(katana.getBySwordsman());
        Assertions.assertNotNull(katana.getSamurai());

        Assertions.assertEquals(katanaDto.getId(), katana.getId());
        Assertions.assertEquals(katanaDto.getNameDto(), katana.getName());
        Assertions.assertEquals(katanaDto.getAgeDto(), katana.getAge());
        Assertions.assertEquals(katanaDto.getBySwordsmanDto(), katana.getBySwordsman());
        Assertions.assertEquals(samuraiDto.getId(), katana.getSamurai().getId());
        Assertions.assertEquals(samuraiDto.getNameDto(), katana.getSamurai().getName());
        Assertions.assertEquals(samuraiDto.getAgeDto(), katana.getSamurai().getAge());
    }

    @Test
    void convertEntityListToDto() {
        Dojo dojo1 = new Dojo(21L, "OralB");
        Dojo dojo2 = new Dojo(22L, "Konoha");
        Samurai samurai1 = new Samurai(1L, "Hinata", 16, List.of(dojo1, dojo2));
        Samurai samurai2 = new Samurai(2L, "Sasuke", 21, List.of(dojo2));

        Katana katana1 = new Katana(11L, "Kusanagi", 100, "Naruto", samurai1);
        Katana katana2 = new Katana(12L, "Samehada", 150, "Sasuke", samurai2);

        List<Katana> entityList = new ArrayList<>();
        entityList.add(katana1);
        entityList.add(katana2);

        List<KatanaDto> dtoList = katanaMapper.toDtoList(entityList);

        Assertions.assertNotNull(dtoList);
        Assertions.assertNotEquals(0, dtoList.size());
        Assertions.assertEquals(entityList.size(), dtoList.size());

        for (int i = 0; i < dtoList.size(); i++) {
            Katana entity = entityList.get(i);
            KatanaDto dto = dtoList.get(i);

            Assertions.assertNotNull(entity);
            Assertions.assertNotNull(dto);

            Assertions.assertEquals(entity.getId(), dto.getId());
            Assertions.assertEquals(entity.getName(), dto.getNameDto());
            Assertions.assertEquals(entity.getAge(), dto.getAgeDto());
            Assertions.assertEquals(entity.getBySwordsman(), dto.getBySwordsmanDto());

            Assertions.assertNotNull(dto.getSamuraiDto());
            Assertions.assertEquals(entity.getSamurai().getId(), dto.getSamuraiDto().getId());
            Assertions.assertEquals(entity.getSamurai().getName(), dto.getSamuraiDto().getNameDto());
            Assertions.assertEquals(entity.getSamurai().getAge(), dto.getSamuraiDto().getAgeDto());
            Assertions.assertEquals(entity.getSamurai().getDojos().size(), dto.getSamuraiDto().getDojos().size());

            for (int j = 0; j < dto.getSamuraiDto().getDojos().size(); j++) {
                Dojo dojoEntity = entity.getSamurai().getDojos().get(j);
                DojoDto dojoDto = dto.getSamuraiDto().getDojos().get(j);
                Assertions.assertEquals(dojoEntity.getId(), dojoDto.getId());
//                Assertions.assertEquals(dojoEntity.getName(), dojoDto.getNameDto());
            }
        }
    }
}