package io.eternalwind.mira.core.models;

import java.util.UUID;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import lombok.experimental.SuperBuilder;

public class EntityTest implements WithAssertions {
    @SuperBuilder
    private static class TestEntity extends Entity {}

    @Test
    void shouldGenerateRandomIdIfNotSet() {
        final Entity.BeforeSaveCallback beforeSaveCallback = new Entity.BeforeSaveCallback();

        final TestEntity entity = TestEntity.builder().build();
        assertThat(entity.getId()).isNull();

        beforeSaveCallback.onBeforeConvert(entity, null);
        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void shouldNotGenerateRandomIdIfSet() {
        final Entity.BeforeSaveCallback beforeSaveCallback = new Entity.BeforeSaveCallback();

        final UUID id = UUID.randomUUID();
        final TestEntity entity = TestEntity.builder().id(id).build();
        beforeSaveCallback.onBeforeConvert(entity, null);

        assertThat(entity.getId()).isEqualTo(id);
    }
}
