package io.eternalwind.mira.persistence.repositories;

import java.util.UUID;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

import io.eternalwind.mira.core.models.Holding;
import io.eternalwind.mira.core.repositories.HoldingRepository;
import io.eternalwind.mira.persistence.mappers.HoldingDaoMapper;
import io.eternalwind.mira.persistence.models.HoldingDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HoldingFirestoreRepository extends HoldingRepository, FirestoreReactiveRepository<HoldingDao> {
    Mono<HoldingDao> findByTicker(String ticker);
    
    default Mono<Holding> save(Holding entity) {
        var toSave = HoldingDaoMapper.INSTANCE.toDao(entity);
        return save(toSave).map(HoldingDaoMapper.INSTANCE::fromDao);
    }

    default Flux<Holding> listAll() {
        return findAll().map(HoldingDaoMapper.INSTANCE::fromDao);
    }

    default Mono<Holding> getById(UUID id) {
        return findById(id.toString()).map(HoldingDaoMapper.INSTANCE::fromDao);
    }

    default Mono<Void> remove(UUID id) {
        return deleteById(id.toString());
    }

    default Mono<Holding> getByTicker(String ticker) {
        return findByTicker(ticker).map(HoldingDaoMapper.INSTANCE::fromDao);
    }
}
