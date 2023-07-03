package io.eternalwind.mira.persistence.mappers;

import java.time.Instant;
import java.util.List;

import io.eternalwind.mira.core.models.Entity;
import io.eternalwind.mira.core.models.InvestmentPlan;
import io.eternalwind.mira.core.models.InvestmentPlan.SectorWeight;

public class InvestmentPlanDaoMapperTest extends FirestoreDaoMapperTestBase {

    @Override
    protected FirestoreDaoMapper getMapper() {
        return InvestmentPlanDaoMapper.INSTANCE;
    }

    @Override
    protected Entity getEntity() {
        return InvestmentPlan.builder()
            .createdTime(Instant.now())
            .lastUpdatedTime(Instant.now())
            .name("name")
            .sectorWeights(List.of(
                new SectorWeight("sector1", 0.2),
                new SectorWeight("sector2", 0.8)
            ))
            .build();
    }
    
}
