package com.example.finalspring.dto;


import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SamuraiDto {
    private Long id;
    private String nameDto;
    private int ageDto;

    List<DojoDto> dojos;
}
