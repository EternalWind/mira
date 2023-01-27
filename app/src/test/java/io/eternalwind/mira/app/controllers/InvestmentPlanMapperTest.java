package io.eternalwind.mira.app.controllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import io.eternalwind.mira.app.dtos.InvestmentPlanDto;
import io.eternalwind.mira.app.dtos.mappers.InvestmentPlanMapper;
import io.eternalwind.mira.core.models.InvestmentPlan;
import io.eternalwind.mira.core.repositories.InvestmentPlanRepository;
import lombok.SneakyThrows;

@AutoConfigureJsonTesters
@WebMvcTest(InvestmentPlansController.class)
public class InvestmentPlanMapperTest implements WithAssertions {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<List<InvestmentPlanDto.Read>> readMultiDtosJson;

    @Autowired
    private JacksonTester<InvestmentPlanDto.Read> readSingleDtoJson;

    @Autowired
    private JacksonTester<InvestmentPlanDto.Create> createDtoJson;

    @Autowired
    private JacksonTester<InvestmentPlanDto.Update> updateDtoJson;

    @MockBean
    private InvestmentPlanMapper mapper;

    @MockBean
    private InvestmentPlanRepository repository;

    @Test
    @SneakyThrows
    void testList() {
        final InvestmentPlan plan1 = mock(InvestmentPlan.class);
        final InvestmentPlan plan2 = mock(InvestmentPlan.class);

        when(repository.findAll()).thenReturn(List.of(plan1, plan2));

        final InvestmentPlanDto.Read planReadDto1 = new InvestmentPlanDto.Read(UUID.randomUUID(), 
                                                                                  "name1", 
                                                                                  Map.of("sector1", 0.3));

        final InvestmentPlanDto.Read planReadDto2 = new InvestmentPlanDto.Read(UUID.randomUUID(), 
                                                                                  "name2", 
                                                                                  Map.of("sector2", 0.5));

        when(mapper.toDto(plan1)).thenReturn(planReadDto1);
        when(mapper.toDto(plan2)).thenReturn(planReadDto2);

        final MockHttpServletResponse response = mvc.perform(
            get("/investment-plans")
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
            readMultiDtosJson.write(List.of(planReadDto1, planReadDto2)).getJson()
        );
    }

    @Test
    @SneakyThrows
    void testFindByTicker() {
        final InvestmentPlan plan1 = mock(InvestmentPlan.class);

        when(repository.findByNameContaining("plan1")).thenReturn(List.of(plan1));

        final InvestmentPlanDto.Read planReadDto1 = new InvestmentPlanDto.Read(UUID.randomUUID(), 
                                                                               "name1", 
                                                                               Map.of());

        when(mapper.toDto(plan1)).thenReturn(planReadDto1);

        final MockHttpServletResponse response = mvc.perform(
            get("/investment-plans/plan1")
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
            readMultiDtosJson.write(List.of(planReadDto1)).getJson()
        );
    }

    @Test
    @SneakyThrows
    void testCreate() {
        final InvestmentPlanDto.Create planCreateDto = new InvestmentPlanDto.Create("name1");

        final InvestmentPlan plan1 = mock(InvestmentPlan.class);

        when(mapper.fromDto(planCreateDto)).thenReturn(plan1);

        final InvestmentPlan savedPlan1 = mock(InvestmentPlan.class);
        when(repository.save(plan1)).thenReturn(savedPlan1);

        final InvestmentPlanDto.Read planReadDto1 = new InvestmentPlanDto.Read(UUID.randomUUID(), 
                                                                               "name1", 
                                                                               Map.of("sector1", 0.5));

        when(mapper.toDto(savedPlan1)).thenReturn(planReadDto1);

        final MockHttpServletResponse response = mvc.perform(
            post("/investment-plans").contentType(MediaType.APPLICATION_JSON).content(
                createDtoJson.write(planCreateDto).getJson()
            )
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
            readSingleDtoJson.write(planReadDto1).getJson()
        );
    }

    @Test
    @SneakyThrows
    void testUpdate() {
        final UUID id = UUID.randomUUID();
        final InvestmentPlanDto.Update planUpdateDto = new InvestmentPlanDto.Update("name1", 
                                                                                    Map.of());

        final InvestmentPlan plan1 = mock(InvestmentPlan.class);
        final InvestmentPlan.InvestmentPlanBuilder plan1Builder = mock(InvestmentPlan.InvestmentPlanBuilder.class);
        when(plan1.toBuilder()).thenReturn(plan1Builder);

        when(repository.findById(id)).thenReturn(Optional.of(plan1));

        final InvestmentPlan updatedPlan1 = mock(InvestmentPlan.class);
        when(mapper.fromDto(planUpdateDto, plan1Builder)).thenReturn(updatedPlan1);
        
        final InvestmentPlan savedPlan1 = mock(InvestmentPlan.class);
        when(repository.save(updatedPlan1)).thenReturn(savedPlan1);

        final InvestmentPlanDto.Read planReadDto1 = new InvestmentPlanDto.Read(UUID.randomUUID(), 
                                                                               "ticker1", 
                                                                               Map.of());

        when(mapper.toDto(savedPlan1)).thenReturn(planReadDto1);

        final MockHttpServletResponse response = mvc.perform(
            put("/investment-plans/" + id.toString()).contentType(MediaType.APPLICATION_JSON).content(
                updateDtoJson.write(planUpdateDto).getJson()
            )
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
            readSingleDtoJson.write(planReadDto1).getJson()
        );
    }

    @Test
    @SneakyThrows
    void testDelete() {
        final UUID id = UUID.randomUUID();

        final MockHttpServletResponse response = mvc.perform(
            delete("/investment-plans/" + id.toString())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(repository).deleteById(id);
    }
}
