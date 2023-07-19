package io.eternalwind.mira.core.repositories;

import io.eternalwind.mira.core.models.Holding;
import reactor.core.publisher.Mono;

public interface HoldingRepository extends Repository<Holding> {
    Mono<Holding> getByTicker(String ticker);
}
