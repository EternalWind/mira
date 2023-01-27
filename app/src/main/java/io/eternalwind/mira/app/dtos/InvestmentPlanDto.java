package io.eternalwind.mira.app.dtos;

import java.util.Map;
import java.util.UUID;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InvestmentPlanDto {
    public static record Create(String name) {}

    public static record Read(UUID id,
                              String name,
                              Map<String, Double> sectorWeights) {}

    public static record Update(String name,
                                Map<String, Double> sectorWeights) {}
}
