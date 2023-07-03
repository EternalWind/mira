// package io.eternalwind.mira.app.controllers;

// import java.util.List;
// import java.util.UUID;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// import io.eternalwind.mira.app.dtos.InvestmentPlanDto;
// import io.eternalwind.mira.app.dtos.mappers.InvestmentPlanMapper;
// import io.eternalwind.mira.core.models.InvestmentPlan;
// import io.eternalwind.mira.core.repositories.InvestmentPlanRepository;
// import lombok.RequiredArgsConstructor;

// @RestController
// @RequiredArgsConstructor
// public class InvestmentPlansController {
//     private static final String ENDPOINT = "investment-plans";

//     private final InvestmentPlanRepository investmentPlanRepository;
//     private final InvestmentPlanMapper investmentPlanMapper;

//     @GetMapping(ENDPOINT)
//     public List<InvestmentPlanDto.Read> list() {
//         return investmentPlanRepository.findAll().stream().map(investmentPlanMapper::toDto).toList();
//     }

//     @GetMapping(ENDPOINT + "/{name}")
//     public List<InvestmentPlanDto.Read> find(@PathVariable("name") String name) {
//         return investmentPlanRepository.findByNameContaining(name).stream().map(investmentPlanMapper::toDto).toList();
//     }

//     @PostMapping(ENDPOINT)
//     public InvestmentPlanDto.Read create(@RequestBody InvestmentPlanDto.Create dto) {
//         final InvestmentPlan saving = investmentPlanMapper.fromDto(dto);
//         final InvestmentPlan saved = investmentPlanRepository.save(saving);

//         return investmentPlanMapper.toDto(saved);
//     }

//     @PutMapping(ENDPOINT + "/{id}")
//     public InvestmentPlanDto.Read update(@PathVariable("id") UUID id, @RequestBody InvestmentPlanDto.Update dto) {
//         return investmentPlanRepository.findById(id)
//             .map(original -> investmentPlanMapper.fromDto(dto, original.toBuilder()))
//             .map(investmentPlanRepository::save)
//             .map(investmentPlanMapper::toDto)
//             .orElseThrow();
//     }

//     @DeleteMapping(ENDPOINT + "/{id}")
//     public void delete(@PathVariable("id") UUID id) {
//         investmentPlanRepository.deleteById(id);
//     }
// }
