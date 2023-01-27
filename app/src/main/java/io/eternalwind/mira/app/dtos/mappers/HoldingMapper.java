package io.eternalwind.mira.app.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.factory.Mappers;

import io.eternalwind.mira.app.dtos.HoldingDto;
import io.eternalwind.mira.core.models.Holding;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = ComponentModel.SPRING)
public interface HoldingMapper {
    HoldingMapper INSTANCE = Mappers.getMapper(HoldingMapper.class);

    HoldingDto.Read toDto(Holding entity);
    Holding fromDto(HoldingDto.Create dto);
    Holding fromDto(HoldingDto.Update dto, @MappingTarget Holding.HoldingBuilder<?,?> entityBuilder);
}