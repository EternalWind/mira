package io.eternalwind.mira.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.eternalwind.mira.core.models.Holding;
import io.eternalwind.mira.persistence.models.HoldingDao;

@Mapper
public interface HoldingDaoMapper extends FirestoreDaoMapper<Holding, HoldingDao> {
    HoldingDaoMapper INSTANCE = Mappers.getMapper(HoldingDaoMapper.class);
}