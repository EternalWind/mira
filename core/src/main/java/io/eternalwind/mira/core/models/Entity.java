package io.eternalwind.mira.core.models;

import java.time.Instant;
import java.util.UUID;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;

@Value
@NonFinal
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder(toBuilder = true)
public abstract class Entity {
    @Builder.Default
    @EqualsAndHashCode.Include
    UUID id = UUID.randomUUID();

    Instant createdTime;
    Instant lastUpdatedTime;
}