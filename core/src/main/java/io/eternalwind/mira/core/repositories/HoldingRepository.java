package io.eternalwind.mira.core.repositories;

import org.apache.commons.lang3.NotImplementedException;

import io.eternalwind.mira.core.models.Holding;

public class HoldingRepository extends EntityRepository<Holding> {
    public Holding findByTicker(String ticker) {
        throw new NotImplementedException();
    }
}
