package io.eternalwind.mira.app.dtos;

import java.util.UUID;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HoldingDto {
    public static record Create(String ticker,
                                String name,
                                Double amount,
                                String sector) {
    }

    public static record Read(UUID id, 
                              String ticker, 
                              String name, 
                              Double amount, 
                              Double price, 
                              String sector, 
                              String createdTime,
                              String lastUpdatedTime) {
    }

    public static record Update(String ticker, 
                                String name, 
                                Double amount, 
                                Double price,
                                String sector) {
    }
}
