package io.eternalwind.mira.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.eternalwind.mira.core.models.InvestmentPlan;
import io.eternalwind.mira.persistence.models.InvestmentPlanDao;

@Mapper
public interface InvestmentPlanDaoMapper extends FirestoreDaoMapper<InvestmentPlan, InvestmentPlanDao> {
    InvestmentPlanDaoMapper INSTANCE = Mappers.getMapper(InvestmentPlanDaoMapper.class);
}
