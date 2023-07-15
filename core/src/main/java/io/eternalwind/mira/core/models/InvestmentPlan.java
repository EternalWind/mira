package io.eternalwind.mira.core.models;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class InvestmentPlan extends Entity {
    public static record SectorWeight(String sector, double weight) {}

    String name;
    List<SectorWeight> sectorWeights;
}
