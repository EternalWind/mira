package io.eternalwind.mira.app.controllers;

import java.util.Collection;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
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
    private final HoldingMapper holdingMapper;

    @GetMapping(ENDPOINT)
    public Collection<HoldingDto.Read> list() {
        log.info("calling list");
        return holdingRepository.findAll().stream().map(holdingMapper::toDto).toList();
    }

    @GetMapping(ENDPOINT + "/{ticker}")
    public HoldingDto.Read find(@PathVariable("ticker") String ticker) {
        log.info("calling find with {}", ticker);
        return holdingMapper.toDto(holdingRepository.findByTicker(ticker));
    }

    @PostMapping(ENDPOINT)
    public HoldingDto.Read create(@RequestBody HoldingDto.Create dto) {
        final Holding saving = holdingMapper.fromDto(dto);
        final Holding saved = holdingRepository.save(saving);

        return holdingMapper.toDto(saved);
    }

    @PutMapping(ENDPOINT + "/{id}")
    public HoldingDto.Read update(@PathVariable("id") UUID id, @RequestBody HoldingDto.Update dto) {
        return holdingRepository.findById(id)
            .map(original -> holdingMapper.fromDto(dto, original.toBuilder()))
            .map(holdingRepository::save)
            .map(holdingMapper::toDto)
            .orElseThrow();
    }

    @DeleteMapping(ENDPOINT + "/{id}")
    public void delete(@PathVariable("id") UUID id) {
        holdingRepository.deleteById(id);
    }
}
