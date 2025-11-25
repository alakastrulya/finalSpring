package com.example.finalspring.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class KatanaDto {
    private Long id;
    private int ageDto;
    private String nameDto;
    private String bySwordsmanDto;
    private SamuraiDto samuraiDto;

}
