package io.eternalwind.mira.core.models;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
public class InvestmentPlan extends Entity {
    public static record SectorWeight(String sector, double weight) {}

    private String name;
    private List<SectorWeight> sectorWeights;
}
