package io.eternalwind.mira.core.repositories;

import java.util.List;

import io.eternalwind.mira.core.models.InvestmentPlan;

public interface InvestmentPlanRepository extends EntityRepository<InvestmentPlan> {
    List<InvestmentPlan> findByNameContaining(String name);
}
