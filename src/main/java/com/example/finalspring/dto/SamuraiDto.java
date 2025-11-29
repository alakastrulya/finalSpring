package com.example.finalspring.dto;


import com.example.finalspring.entity.Dojo;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SamuraiDto {
    private Long id;

    private String nameDto;

    private int ageDto;

    List<DojoDto> dojos;
}