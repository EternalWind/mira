package io.eternalwind.mira.core.models;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder(toBuilder = true)
@ToString
public abstract class Entity {
    @EqualsAndHashCode.Include
    private UUID id;
}
