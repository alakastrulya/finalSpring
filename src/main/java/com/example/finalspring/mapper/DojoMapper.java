package com.example.finalspring.mapper;

import com.example.finalspring.dto.DojoDto;
import com.example.finalspring.entity.Dojo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DojoMapper {

    DojoDto toDto(Dojo dojo);
    Dojo toEntity(DojoDto dojoDto);
    List<DojoDto> toDtoList(List<Dojo> dojoList);
}
