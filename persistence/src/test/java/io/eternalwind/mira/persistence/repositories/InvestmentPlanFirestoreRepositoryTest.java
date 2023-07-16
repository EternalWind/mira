package io.eternalwind.mira.persistence.repositories;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.FirestoreEmulatorContainer;
import org.testcontainers.junit.jupiter.Container;
import io.eternalwind.mira.core.models.InvestmentPlan;
import io.eternalwind.mira.core.models.InvestmentPlan.SectorWeight;
import io.eternalwind.mira.core.repositories.InvestmentPlanRepository;

public class InvestmentPlanFirestoreRepositoryTest
        extends FirestoreRepositoryTestBase<InvestmentPlan, InvestmentPlanRepository> {

    @Container
    private static final FirestoreEmulatorContainer EMULATOR = createFirestoreEmulatorContainer();

    @DynamicPropertySource
    private static void emulatorProperties(DynamicPropertyRegistry registry) {
        configureFirestoreEmulator(registry, EMULATOR);
    }

    @Override
    protected InvestmentPlan createEntity() {
        return InvestmentPlan.builder()
                .createdTime(Instant.now())
                .id(UUID.randomUUID())
                .lastUpdatedTime(Instant.now())
                .name(RandomStringUtils.randomAscii(100))
                .sectorWeights(List.of(
                        new SectorWeight(RandomStringUtils.randomAscii(100), RandomUtils.nextDouble()),
                        new SectorWeight(RandomStringUtils.randomAscii(100), RandomUtils.nextDouble())))
                .build();
    }

}
