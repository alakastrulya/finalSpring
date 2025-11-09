package com.example.finalspring.dto;

import com.example.finalspring.entity.Samurai;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DojoDto {
    private Long id;

    private String name;

    List<Samurai> samuraiList;
}
