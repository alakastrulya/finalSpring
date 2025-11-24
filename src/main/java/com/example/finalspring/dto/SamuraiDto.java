package com.example.finalspring.dto;


import com.example.finalspring.entity.Dojo;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SamuraiDto {
    private Long id;

    private String nameDto;

    private int ageDto;

    List<Dojo> dojos;
}
