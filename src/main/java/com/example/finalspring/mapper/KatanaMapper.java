package com.example.finalspring.mapper;

import com.example.finalspring.dto.KatanaDto;
import com.example.finalspring.entity.Katana;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = SamuraiMapper.class)
public interface KatanaMapper {

    @Mapping(target = "nameDto", source = "name")
    @Mapping(target = "ageDto", source = "age")
    @Mapping(target = "bySwordsmanDto", source = "bySwordsman")
    @Mapping(target = "samuraiDto", source = "samurai")
    KatanaDto toDto(Katana katana);
    @Mapping(target = "name", source = "nameDto")
    @Mapping(target = "age", source = "ageDto")
    @Mapping(target = "bySwordsman", source = "bySwordsmanDto")
    @Mapping(target = "samurai", source = "samuraiDto")
    Katana toEntity(KatanaDto katanaDto);
    List<KatanaDto> toDtoList(List<Katana> katanaList);
    List<Katana> toEntityList(List<KatanaDto> katanaDtos);
}