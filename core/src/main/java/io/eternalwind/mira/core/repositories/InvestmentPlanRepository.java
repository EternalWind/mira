package io.eternalwind.mira.core.repositories;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

import io.eternalwind.mira.core.models.InvestmentPlan;

public class InvestmentPlanRepository extends EntityRepository<InvestmentPlan> {
    public List<InvestmentPlan> findByNameContaining(String name) {
        throw new NotImplementedException();
    }
}
