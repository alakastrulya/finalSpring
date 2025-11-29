package com.example.finalspring.dto;

import com.example.finalspring.entity.Samurai;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KatanaDto {
    private Long id;
    private int ageDto;
    private String nameDto;
    private String bySwordsmanDto;
    private SamuraiDto samuraiDto;

}