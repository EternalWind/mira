package io.eternalwind.mira.core.models;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;


@Value
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Holding extends Entity {
    String ticker;
    String name;
    Double amount;
    Double price;
    String sector;
}
