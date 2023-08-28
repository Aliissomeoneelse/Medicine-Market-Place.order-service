package com.example.orderservice.mapper;

import com.example.orderservice.dto.CreditDto;
import com.example.orderservice.module.Credit;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface CreditMapper {
    CreditDto toDto(Credit credit);

    @Mapping(target = "creditId", ignore = true)
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Credit toEntity(CreditDto dto);

    @Mapping(target = "creditId", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Credit credit, CreditDto dto);
}
