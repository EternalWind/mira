package io.eternalwind.mira.app.dtos.mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.factory.Mappers;

import io.eternalwind.mira.app.dtos.InvestmentPlanDto;
import io.eternalwind.mira.core.models.InvestmentPlan;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = ComponentModel.SPRING)
public interface InvestmentPlanMapper {
    InvestmentPlanMapper INSTANCE = Mappers.getMapper(InvestmentPlanMapper.class);
    String OTHER_SECTORS = "Others";

    InvestmentPlanDto.Read toDto(InvestmentPlan entity);
    InvestmentPlan fromDto(InvestmentPlanDto.Create dto);
    InvestmentPlan fromDto(InvestmentPlanDto.Update dto, @MappingTarget InvestmentPlan.InvestmentPlanBuilder<?,?> entityBuilder);

    default List<InvestmentPlan.SectorWeight> fromSectorWeightsDto(Map<String, Double> dto) {
        if (dto.isEmpty()) {
            return null;
        }

        return dto.entrySet().stream()
            .filter(e -> !e.getKey().equals(OTHER_SECTORS))
            .map(e -> new InvestmentPlan.SectorWeight(e.getKey(), e.getValue()))
            .toList();
    }

    default Map<String, Double> toSectorWeightsDto(List<InvestmentPlan.SectorWeight> entity) {
        if (entity == null) {
            return Map.of();
        }

        final Map<String, Double> aggregated = entity.stream().collect(Collectors.toMap(
            InvestmentPlan.SectorWeight::sector, InvestmentPlan.SectorWeight::weight, Double::sum));

        final Double allocatedPercentage = aggregated.values().stream().reduce(0.0, Double::sum);

        aggregated.merge(OTHER_SECTORS, 1.0 - allocatedPercentage, (a, b) -> b);

        return aggregated;
    }
}