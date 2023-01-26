package io.eternalwind.mira.core.models;

import java.math.BigDecimal;
import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Document
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

    @CreatedDate
    private Instant createdTime;

    @LastModifiedDate
    private Instant lastUpdatedTime;
}
