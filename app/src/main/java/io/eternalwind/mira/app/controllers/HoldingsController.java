package io.eternalwind.mira.app.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.eternalwind.mira.app.dtos.HoldingDto;
import io.eternalwind.mira.app.dtos.mappers.HoldingMapper;
import io.eternalwind.mira.core.models.Holding;
import io.eternalwind.mira.core.repositories.HoldingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/holdings")
@RequiredArgsConstructor
@Slf4j
public class HoldingsController {
    private final HoldingRepository holdingRepository;
    private final HoldingMapper mapper;

    @GetMapping()
    public Flux<HoldingDto.Read> list() {
        log.info("calling list");
        return holdingRepository.listAll().map(mapper::toDto);
    }

    @GetMapping("/{id}")
    public Mono<HoldingDto.Read> find(@PathVariable("id") UUID id) {
        log.info("calling find with {}", id);
        return holdingRepository.getById(id).map(mapper::toDto);
    }

    @GetMapping("/ticker/{ticker}")
    public Mono<HoldingDto.Read> findByTicker(@PathVariable("ticker") String ticker) {
        log.info("caling find with ticker {}", ticker);
        return holdingRepository.getByTicker(ticker).map(mapper::toDto);
    }

    @PostMapping()
    public Mono<HoldingDto.Read> create(@RequestBody HoldingDto.Create dto) {
        log.info("Creating {}", dto);
        return holdingRepository.save(mapper.fromDto(dto)).map(mapper::toDto);
    }

    @PutMapping("/{id}")
    public Mono<HoldingDto.Read> update(@PathVariable("id") UUID id, @RequestBody HoldingDto.Update dto) {
        log.info("Updating {} to {}", id, dto);
        return holdingRepository.getById(id)
                                .map(foundEntity -> mapper.fromDto(dto, foundEntity.toBuilder()))
                                .flatMap(holdingRepository::save)
                                .map(mapper::toDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") UUID id) {
        return holdingRepository.remove(id);
    }
}
