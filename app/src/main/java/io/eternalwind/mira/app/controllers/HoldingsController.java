package io.eternalwind.mira.app.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping()
    public Flux<Holding> list() {
        log.info("calling list");
        return holdingRepository.listAll();
    }

    @GetMapping("/{id}")
    public Mono<Holding> find(@PathVariable("id") String id) {
        log.info("calling find with {}", id);
        return holdingRepository.getById(UUID.fromString(id));
    }

    // @PostMapping(ENDPOINT)
    // public HoldingDto.Read create(@RequestBody HoldingDto.Create dto) {
    //     final Holding saving = holdingMapper.fromDto(dto);
    //     final Holding saved = holdingRepository.save(saving);

    //     return holdingMapper.toDto(saved);
    // }

    // @PutMapping(ENDPOINT + "/{id}")
    // public HoldingDto.Read update(@PathVariable("id") UUID id, @RequestBody HoldingDto.Update dto) {
    //     return holdingRepository.findById(id)
    //         .map(original -> holdingMapper.fromDto(dto, original.toBuilder()))
    //         .map(holdingRepository::save)
    //         .map(holdingMapper::toDto)
    //         .orElseThrow();
    // }

    // @DeleteMapping(ENDPOINT + "/{id}")
    // public void delete(@PathVariable("id") UUID id) {
    //     holdingRepository.deleteById(id);
    // }
}
