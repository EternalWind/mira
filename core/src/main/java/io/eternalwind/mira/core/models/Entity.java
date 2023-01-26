package io.eternalwind.mira.core.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;

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
    public static class BeforeSaveCallback implements BeforeConvertCallback<Entity> {

        @Override
        public Entity onBeforeConvert(Entity entity, String collection) {
            if (entity.id == null) {
                entity.id = UUID.randomUUID();
            }

            return entity;
        }

    }

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
}
