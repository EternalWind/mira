package io.eternalwind.mira.persistence.mappers;

import java.time.Instant;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import com.google.cloud.Timestamp;

import io.eternalwind.mira.core.models.Entity;

public abstract class FirestoreDaoMapperTestBase implements WithAssertions {
    private final FirestoreDaoMapper mapper = getMapper();

    @Test
    void testTimestampToInstant() {
        var before = Timestamp.now();
        var instant = mapper.timestampToInstant(before);
        var after = mapper.instantToTimestamp(instant);

        assertThat(after).isEqualTo(before);
    }

    @Test
    void testTimestampToInstantWithNull() {
        assertThat(mapper.timestampToInstant(null)).isNull();
    }

    @Test
    void testInstantToTimestamp() {
        var before = Instant.now();
        var instant = mapper.instantToTimestamp(before);
        var after = mapper.timestampToInstant(instant);

        assertThat(after).isEqualTo(before);
    }

    @Test
    void testInstantToTimestampWithNull() {
        assertThat(mapper.instantToTimestamp(null)).isNull();
    }

    @Test
    void testConversion() {
        var before = getEntity();
        var dao = mapper.toDao(before);
        var after = mapper.fromDao(dao);
    }

    protected abstract FirestoreDaoMapper getMapper();

    protected abstract Entity getEntity();
}
