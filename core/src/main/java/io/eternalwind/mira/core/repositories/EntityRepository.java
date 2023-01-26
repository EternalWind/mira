package io.eternalwind.mira.core.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import io.eternalwind.mira.core.models.Entity;

@NoRepositoryBean
public interface EntityRepository<T extends Entity> extends MongoRepository<T, UUID> {
    
}
