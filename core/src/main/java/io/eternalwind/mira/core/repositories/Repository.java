package io.eternalwind.mira.core.repositories;

import java.util.UUID;

import io.eternalwind.mira.core.models.Entity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Repository<T extends Entity> {
    Mono<T> save(T entity);
    Flux<T> listAll();
    Mono<T> getById(UUID id);
    Mono<Void> remove(UUID id);
}
