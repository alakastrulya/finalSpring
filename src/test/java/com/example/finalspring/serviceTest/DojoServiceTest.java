package com.example.finalspring.serviceTest;

import com.example.finalspring.dto.DojoDto;
import com.example.finalspring.service.DojoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class DojoServiceTest {
    @Autowired
    private DojoService dojoService;

    @Test
    void getAll(){
        List<DojoDto> list =dojoService.getAll();

        assertNotNull(list);
        assertNotEquals(0,list.size());

        for(int i=0;i< list.size();i++){
            DojoDto dojoDto = list.get(i);
            assertNotNull(dojoDto);
            assertNotNull(dojoDto.getNameDto());
        }
    }


    @Test
    void getById(){
        Random random = new Random();
        int randomIndex = random.nextInt(dojoService.getAll().size());

        Long someIndex = dojoService.getAll().get(randomIndex).getId();

        DojoDto dojoDto = dojoService.getById(someIndex);

        assertNotNull(dojoDto);

        assertNotNull(dojoDto.getId());
        assertNotNull(dojoDto.getNameDto());

        DojoDto check = dojoService.getById(-1L);

        assertNull(check);

    }

    @Test
    void add(){
        DojoDto dto = DojoDto.builder()
                .nameDto("Makeba")
                .build();

        DojoDto createdItem = dojoService.addDojo(dto);

        assertNotNull(createdItem);
        assertNotNull(createdItem.getId());
        assertNotNull(createdItem.getNameDto());

        assertEquals(dto.getNameDto(),createdItem.getNameDto() );

        DojoDto get = dojoService.getById(createdItem.getId());

        assertNotNull(get);
        assertNotNull(get.getId());

        assertEquals(get.getNameDto(),createdItem.getNameDto() );

    }

    @Test
    void update(){

        Random random = new Random();
        int randomIndex = random.nextInt(dojoService.getAll().size());
        Long someindex = dojoService.getAll().get(randomIndex).getId();

        DojoDto dto = new DojoDto();
        dto.setId(someindex);
        dto.setNameDto("tapishke");


        DojoDto updated = dojoService.updateDojo(dto.getId(),dto);
        assertNotNull(updated);
        assertNotNull(updated.getId());
        assertNotNull(updated.getNameDto());

        assertEquals(dto.getNameDto(),updated.getNameDto() );

        DojoDto check = dojoService.getById(someindex);

        assertNotNull(check);
        assertNotNull(check.getId());
        assertNotNull(check.getNameDto());

        assertEquals(check.getNameDto(),dto.getNameDto() );
        assertEquals(check.getNameDto(),dto.getNameDto() );
        assertEquals(check.getNameDto(),dto.getNameDto());

    }

    @Test
    void delete(){
        Random random = new Random();
        int randomIndex = random.nextInt(dojoService.getAll().size());

        Long someindex = dojoService.getAll().get(randomIndex).getId();

        assertTrue(dojoService.deleteDojo(someindex));

        DojoDto checkAnimals = dojoService.getById(someindex);
        assertNull(checkAnimals);

    }

    //нужно добавить проверку листа внутри него

}
