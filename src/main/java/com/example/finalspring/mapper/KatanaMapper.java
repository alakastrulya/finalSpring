package com.example.finalspring.mapper;

import com.example.finalspring.dto.KatanaDto;
import com.example.finalspring.entity.Katana;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KatanaMapper {

    KatanaDto toDto(Katana katana);
    Katana toEntity(KatanaDto katanaDto);
    List<KatanaDto> toDtoList(List<Katana> katanaList);
}
