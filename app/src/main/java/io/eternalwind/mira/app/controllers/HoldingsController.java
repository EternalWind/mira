package io.eternalwind.mira.app.controllers;

import java.util.Collection;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.eternalwind.mira.app.dtos.HoldingDto;
import io.eternalwind.mira.app.dtos.mappers.HoldingMapper;
import io.eternalwind.mira.core.models.Holding;
import io.eternalwind.mira.core.repositories.HoldingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HoldingsController {
    private static final String ENDPOINT = "holdings";

    private final HoldingRepository holdingRepository;

    @GetMapping(ENDPOINT)
    public Collection<HoldingDto.Read> list() {
        log.info("calling list");
        return holdingRepository.findAll().stream().map(HoldingMapper.INSTANCE::toDto).toList();
    }

    @GetMapping(ENDPOINT + "/{ticker}")
    public HoldingDto.Read find(@PathVariable("ticker") String ticker) {
        log.info("calling find with {}", ticker);
        return HoldingMapper.INSTANCE.toDto(holdingRepository.findByTicker(ticker));
    }

    @PostMapping(ENDPOINT)
    public Holding create(@RequestBody HoldingDto.Create dto) {
        return holdingRepository.save(HoldingMapper.INSTANCE.fromDto(dto));
    }

    @PutMapping(ENDPOINT + "/{id}")
    public Holding update(@PathVariable("id") UUID id, @RequestBody HoldingDto.Update dto) {
        return holdingRepository.findById(id)
            .map(original -> HoldingMapper.INSTANCE.fromDto(dto, original.toBuilder()))
            .map(holdingRepository::save)
            .orElseThrow();
    }
}
