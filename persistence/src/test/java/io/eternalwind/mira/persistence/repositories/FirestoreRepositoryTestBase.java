package io.eternalwind.mira.persistence.repositories;

import java.util.List;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.FirestoreEmulatorContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.cloud.spring.data.firestore.repository.config.EnableReactiveFirestoreRepositories;

import io.eternalwind.mira.core.models.Entity;
import io.eternalwind.mira.core.repositories.Repository;
import io.eternalwind.mira.persistence.PersistenceModule;

@SpringBootTest(classes = { PersistenceModule.class })
@EnableReactiveFirestoreRepositories
@Testcontainers
@EnableAutoConfiguration
public abstract class FirestoreRepositoryTestBase<E extends Entity, R extends Repository> implements WithAssertions {
    @TestConfiguration
    private static class EmulatorConfiguration {
        // By default, autoconfiguration will initialize application default
        // credentials.
        // For testing purposes, don't use any credentials. Bootstrap w/
        // NoCredentialsProvider.
        @Bean
        CredentialsProvider googleCredentials() {
            return NoCredentialsProvider.create();
        }
    }

    @Autowired
    protected R repository;

    protected static FirestoreEmulatorContainer createFirestoreEmulatorContainer() {
        return new FirestoreEmulatorContainer(
                DockerImageName.parse("gcr.io/google.com/cloudsdktool/google-cloud-cli:380.0.0-emulators"));
    }

    protected static void configureFirestoreEmulator(DynamicPropertyRegistry registry,
            FirestoreEmulatorContainer emulator) {
        registry.add("spring.cloud.gcp.firestore.host-port", emulator::getEmulatorEndpoint);
    }

    @Test
    void testSave() {
        var entity = createEntity();
        var saved = repository.save(entity).block();

        assertThat(saved).usingRecursiveComparison().ignoringFields(ignoringFields().toArray(new String[0])).isEqualTo(entity);
    }
    
    @Test
    void testListAll() {
        var entity = createEntity();
        repository.save(entity).block();

        var listed = (List)repository.listAll().collectList().block();

        assertThat(listed).isNotEmpty();
        assertThat(listed).usingRecursiveFieldByFieldElementComparatorIgnoringFields(ignoringFields().toArray(new String[0])).contains(entity); 
    }

    @Test
    void testGetById() {
        var entity = createEntity();
        repository.save(entity).block();

        var found = repository.getById(entity.getId()).block();

        assertThat(found).usingRecursiveComparison().ignoringFields(ignoringFields().toArray(new String[0]))
                .isEqualTo(entity);
    }

    @Test
    void testRemove() {
        var entity = createEntity();
        repository.save(entity).block();

        var foundBeforeRemoval = repository.getById(entity.getId()).block();
        assertThat(foundBeforeRemoval).isNotNull();

        repository.remove(entity.getId()).block();

        var foundAfterRemoval = repository.getById(entity.getId()).block();
        assertThat(foundAfterRemoval).isNull();
    }

    protected List<String> ignoringFields() {
        return List.of("createdTime", "lastUpdatedTime");
    }

    protected abstract E createEntity();
}
