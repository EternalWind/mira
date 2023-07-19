package io.eternalwind.mira.persistence.repositories;

import java.time.Instant;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.FirestoreEmulatorContainer;
import org.testcontainers.junit.jupiter.Container;

import io.eternalwind.mira.core.models.Holding;
import io.eternalwind.mira.core.repositories.HoldingRepository;

public class HoldingFirestoreRepositoryTest extends FirestoreRepositoryTestBase<Holding, HoldingRepository> {

    @Container
    private static final FirestoreEmulatorContainer EMULATOR = createFirestoreEmulatorContainer();

    @DynamicPropertySource
    private static void emulatorProperties(DynamicPropertyRegistry registry) {
        configureFirestoreEmulator(registry, EMULATOR);
    }

    @Override
    protected Holding createEntity() {
        return Holding.builder()
                .amount(RandomUtils.nextDouble())
                .createdTime(Instant.now())
                .id(UUID.randomUUID())
                .lastUpdatedTime(Instant.now())
                .name(RandomStringUtils.randomAscii(100))
                .price(RandomUtils.nextDouble())
                .sector(RandomStringUtils.randomAscii(100))
                .ticker(RandomStringUtils.randomAlphabetic(4))
                .build();
    }

    @Test
    void testGetByTicker() {
        var expected = createEntity();
        repository.save(expected).block();

        var actual = repository.getByTicker(expected.getTicker()).block();
        assertThat(actual).isEqualTo(expected);
    }
}
