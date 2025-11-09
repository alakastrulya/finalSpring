package com.example.finalspring.dto;

import com.example.finalspring.entity.Samurai;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KatanaDto {
    private Long id;

    private String name;

    private int age;

    private String bySwordsman;

    private Samurai samurai;

}
