package com.example.finalspring.dto;

import com.example.finalspring.entity.Samurai;
import jakarta.persistence.ManyToMany;
import lombok.*;


import java.util.List;

@Setter
@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DojoDto {
    private Long id;
    private String nameDto;

    List<SamuraiDto> samuraiList;
}
