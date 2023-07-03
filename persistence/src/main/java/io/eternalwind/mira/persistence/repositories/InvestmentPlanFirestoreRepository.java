package io.eternalwind.mira.persistence.repositories;

import java.util.UUID;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

import io.eternalwind.mira.core.models.InvestmentPlan;
import io.eternalwind.mira.core.repositories.InvestmentPlanRepository;
import io.eternalwind.mira.persistence.mappers.InvestmentPlanDaoMapper;
import io.eternalwind.mira.persistence.models.InvestmentPlanDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InvestmentPlanFirestoreRepository extends InvestmentPlanRepository, FirestoreReactiveRepository<InvestmentPlanDao> {
    default Mono<InvestmentPlan> save(InvestmentPlan entity) {
        var dao = InvestmentPlanDaoMapper.INSTANCE.toDao(entity);
        return save(dao).map(InvestmentPlanDaoMapper.INSTANCE::fromDao);
    }

    default Flux<InvestmentPlan> listAll() {
        return findAll().map(InvestmentPlanDaoMapper.INSTANCE::fromDao);
    }

    default Mono<InvestmentPlan> getById(UUID id) {
        return findById(id.toString()).map(InvestmentPlanDaoMapper.INSTANCE::fromDao);
    }

    default Mono<Void> remove(UUID id) {
        return deleteById(id.toString());
    }
}
