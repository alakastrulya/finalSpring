package com.example.finalspring.mapperTest;
import com.example.finalspring.dto.DojoDto;
import com.example.finalspring.dto.SamuraiDto;
import com.example.finalspring.entity.Dojo;
import com.example.finalspring.mapper.DojoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DojoMapperTest {
    @Autowired
    private DojoMapper dojoMapper;
    @Test
    void convertEntityToDto(){
        Dojo dojo = new Dojo();
        dojo.setId(2L);
        dojo.setName("Vrata Ada");

        DojoDto dojoDto = dojoMapper.toDto(dojo);
        Assertions.assertNotNull(dojoDto);
        Assertions.assertNotNull(dojoDto.getId());
        Assertions.assertNotNull(dojoDto.getNameDto());

        Assertions.assertEquals(dojo.getId(),dojoDto.getId());
        Assertions.assertNotNull(dojo.getName(), dojoDto.getNameDto());

    }
    @Test
    void convertDtoToEntity(){
        SamuraiDto samuraiDto = new SamuraiDto();
        samuraiDto.setId(10L);
        samuraiDto.setNameDto("Naruto");
        List<SamuraiDto> samuraiList = new ArrayList<>();
        samuraiList.add(samuraiDto);

        DojoDto dojoDto = new DojoDto();
        dojoDto.setId(2L);
        dojoDto.setNameDto("Vrata Ada");

        Dojo dojo = dojoMapper.toEntity(dojoDto);
        Assertions.assertNotNull(dojo);
        Assertions.assertNotNull(dojo.getId());
        Assertions.assertNotNull(dojo.getName());

        Assertions.assertEquals(dojo.getId(),dojoDto.getId());
        Assertions.assertEquals(dojo.getName(),dojoDto.getNameDto());


    }
    @Test
    void convertEntityListToDtoList(){
        List<Dojo> entityList = new ArrayList<>();
        entityList.add(new Dojo(3L,"Hidden Leaf Village"));
        entityList.add(new Dojo(4L,"(Hidden Sand Village"));
        entityList.add(new Dojo(5L, "Land of Lightning"));

        List<DojoDto> dtoList = dojoMapper.toDtoList(entityList);
        Assertions.assertNotNull(dtoList);
        Assertions.assertNotEquals(0,dtoList.size());
        Assertions.assertEquals(entityList.size(), dtoList.size());

        for (int i = 0; i < dtoList.size(); i++) {
            Dojo entity = entityList.get(i);
            DojoDto dto = dtoList.get(i);

            Assertions.assertNotNull(entity);
            Assertions.assertNotNull(dto);

            Assertions.assertEquals(entity.getId(),dto.getId());
            Assertions.assertEquals(entity.getName(),dto.getNameDto());
        }
    }
}
