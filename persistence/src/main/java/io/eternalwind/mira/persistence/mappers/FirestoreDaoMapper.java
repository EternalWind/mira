package io.eternalwind.mira.persistence.mappers;

import java.time.Instant;
import java.util.Optional;

import com.google.cloud.Timestamp;

public interface FirestoreDaoMapper<E, D> {
    default Timestamp instantToTimestamp(Instant instant) {
        return Optional.ofNullable(instant)
            .map(i -> Timestamp.ofTimeSecondsAndNanos(i.getEpochSecond(), i.getNano()))
            .orElse(null);
    }

    default Instant timestampToInstant(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
            .map(ts -> Instant.ofEpochSecond(ts.getSeconds(), ts.getNanos()))
            .orElse(null);
    }

    E fromDao(D dao);

    D toDao(E entity);
}
