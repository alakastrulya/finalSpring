package com.example.finalspring.mapper;

import com.example.finalspring.dto.DojoDto;
import com.example.finalspring.entity.Dojo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DojoMapper {
    @Mapping(target = "nameDto", source = "name")
    DojoDto toDto(Dojo dojo);
    @Mapping(target = "name", source = "nameDto")
    Dojo toEntity(DojoDto dojoDto);
    List<DojoDto> toDtoList(List<Dojo> dojoList);
    List<Dojo> toEntityList(List<DojoDto> dojoDtoList);
}
