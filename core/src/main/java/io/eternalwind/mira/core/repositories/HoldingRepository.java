package io.eternalwind.mira.core.repositories;

import io.eternalwind.mira.core.models.Holding;

public interface HoldingRepository extends EntityRepository<Holding> {
    Holding findByTicker(String ticker);
}
