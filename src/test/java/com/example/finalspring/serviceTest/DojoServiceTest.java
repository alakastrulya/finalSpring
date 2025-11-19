package com.example.finalspring.serviceTest;

import com.example.finalspring.dto.DojoDto;
import com.example.finalspring.service.DojoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
            assertNotNull(dojoDto.getName());
        }

    }

}
