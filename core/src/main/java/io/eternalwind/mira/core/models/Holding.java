package io.eternalwind.mira.core.models;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
public class Holding extends Entity {
    private String ticker;
    private String name;
    private BigDecimal amount;
    private BigDecimal price;
    private String sector;

    private Instant createdTime;
    private Instant lastUpdatedTime;
}
