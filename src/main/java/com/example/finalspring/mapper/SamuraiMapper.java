package com.example.finalspring.mapper;

import com.example.finalspring.dto.SamuraiDto;
import com.example.finalspring.entity.Samurai;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses =  DojoMapper.class)
public interface SamuraiMapper {

        @Mapping(target = "ageDto", source = "age")
        @Mapping(target = "nameDto", source = "name")
        SamuraiDto toDto(Samurai samurai);
        @Mapping(target = "age", source = "ageDto")
        @Mapping(target = "name", source = "nameDto")
        Samurai toEntity(SamuraiDto samuraiDto);
        @Mapping(target = "dojosDto", source = "dojos")
        @Mapping(target = "dojos", source = "dojosDto")
        List<SamuraiDto> toDtoList(List<Samurai> samuraiList);
        List<Samurai> toEntityList(List<SamuraiDto> samuraiDtoList);

}
