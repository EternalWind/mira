package io.eternalwind.mira.core.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.NotImplementedException;

import io.eternalwind.mira.core.models.Entity;

public class EntityRepository<T extends Entity> {
    public List<T> findAll() {
        throw new NotImplementedException();
    }
    
    public Optional<T> findById(UUID id) {
        throw new NotImplementedException();
    }

    public T save(T saving) {
        throw new NotImplementedException();
    }

    public void deleteById(UUID id) {
        throw new NotImplementedException();
    }
}
