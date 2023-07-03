package io.eternalwind.mira.persistence.mappers;

import java.time.Instant;

import io.eternalwind.mira.core.models.Entity;
import io.eternalwind.mira.core.models.Holding;

public class HoldingDaoMapperTest extends FirestoreDaoMapperTestBase {

    @Override
    protected FirestoreDaoMapper getMapper() {
        return HoldingDaoMapper.INSTANCE;
    }

    @Override
    protected Entity getEntity() {
        return Holding.builder()
            .createdTime(Instant.now())
            .lastUpdatedTime(Instant.now())
            .name("name")
            .amount(1.0)
            .price(20.3)
            .sector("sector")
            .ticker("ticker")
            .build();
    }
    
}
