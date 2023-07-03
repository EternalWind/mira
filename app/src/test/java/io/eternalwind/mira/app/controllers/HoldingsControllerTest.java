// package io.eternalwind.mira.app.controllers;

// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

// import java.math.BigDecimal;
// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;

// import org.assertj.core.api.WithAssertions;
// import org.junit.jupiter.api.Disabled;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.json.JacksonTester;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.mock.web.MockHttpServletResponse;
// import org.springframework.test.web.servlet.MockMvc;

// import io.eternalwind.mira.app.dtos.HoldingDto;
// import io.eternalwind.mira.app.dtos.mappers.HoldingMapper;
// import io.eternalwind.mira.core.models.Holding;
// import io.eternalwind.mira.core.repositories.HoldingRepository;
// import lombok.SneakyThrows;

// @AutoConfigureJsonTesters
// @WebMvcTest(HoldingsController.class)
// @Disabled
// public class HoldingsControllerTest implements WithAssertions {
//     @Autowired
//     private MockMvc mvc;

//     @Autowired
//     private JacksonTester<List<HoldingDto.Read>> readMultiDtosJson;

//     @Autowired
//     private JacksonTester<HoldingDto.Read> readSingleDtoJson;

//     @Autowired
//     private JacksonTester<HoldingDto.Create> createDtoJson;

//     @Autowired
//     private JacksonTester<HoldingDto.Update> updateDtoJson;

//     @MockBean
//     private HoldingMapper mapper;

//     @MockBean
//     private HoldingRepository repository;

//     @Test
//     @SneakyThrows
//     void testList() {
//         final Holding holding1 = mock(Holding.class);
//         final Holding holding2 = mock(Holding.class);

//         when(repository.findAll()).thenReturn(List.of(holding1, holding2));

//         final HoldingDto.Read holdingReadDto1 = new HoldingDto.Read(UUID.randomUUID(), 
//                                                                     "ticker1", 
//                                                                     "name1", 
//                                                                     BigDecimal.valueOf(10), 
//                                                                     BigDecimal.valueOf(100.4), 
//                                                                     "sector1", 
//                                                                     "createdTime1", 
//                                                                     "updatedTime1");

//         final HoldingDto.Read holdingReadDto2 = new HoldingDto.Read(UUID.randomUUID(), 
//                                                                     "ticker2", 
//                                                                     "name2", 
//                                                                     BigDecimal.valueOf(140), 
//                                                                     BigDecimal.valueOf(102.34), 
//                                                                     "sector2", 
//                                                                     "createdTime2", 
//                                                                     "updatedTime2");

//         when(mapper.toDto(holding1)).thenReturn(holdingReadDto1);
//         when(mapper.toDto(holding2)).thenReturn(holdingReadDto2);

//         final MockHttpServletResponse response = mvc.perform(
//             get("/holdings")
//         ).andReturn().getResponse();

//         assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//         assertThat(response.getContentAsString()).isEqualTo(
//             readMultiDtosJson.write(List.of(holdingReadDto1, holdingReadDto2)).getJson()
//         );
//     }

//     @Test
//     @SneakyThrows
//     void testFindByTicker() {
//         final Holding holding1 = mock(Holding.class);

//         when(repository.findByTicker("ticker1")).thenReturn(holding1);

//         final HoldingDto.Read holdingReadDto1 = new HoldingDto.Read(UUID.randomUUID(), 
//                                                                     "ticker1", 
//                                                                     "name1", 
//                                                                     BigDecimal.valueOf(10), 
//                                                                     BigDecimal.valueOf(100.4), 
//                                                                     "sector1", 
//                                                                     "createdTime1", 
//                                                                     "updatedTime1");

//         when(mapper.toDto(holding1)).thenReturn(holdingReadDto1);

//         final MockHttpServletResponse response = mvc.perform(
//             get("/holdings/ticker1")
//         ).andReturn().getResponse();

//         assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//         assertThat(response.getContentAsString()).isEqualTo(
//             readSingleDtoJson.write(holdingReadDto1).getJson()
//         );
//     }

//     @Test
//     @SneakyThrows
//     void testCreate() {
//         final HoldingDto.Create holdingCreateDto = new HoldingDto.Create("ticker1", 
//                                                                          "name1", 
//                                                                          BigDecimal.valueOf(50), 
//                                                                          "sector1");

//         final Holding holding1 = mock(Holding.class);

//         when(mapper.fromDto(holdingCreateDto)).thenReturn(holding1);

//         final Holding savedHolding1 = mock(Holding.class);
//         when(repository.save(holding1)).thenReturn(savedHolding1);

//         final HoldingDto.Read holdingReadDto1 = new HoldingDto.Read(UUID.randomUUID(), 
//                                                                     "ticker1", 
//                                                                     "name1", 
//                                                                     BigDecimal.valueOf(10), 
//                                                                     BigDecimal.valueOf(100.4), 
//                                                                     "sector1", 
//                                                                     "createdTime1", 
//                                                                     "updatedTime1");

//         when(mapper.toDto(savedHolding1)).thenReturn(holdingReadDto1);

//         final MockHttpServletResponse response = mvc.perform(
//             post("/holdings").contentType(MediaType.APPLICATION_JSON).content(
//                 createDtoJson.write(holdingCreateDto).getJson()
//             )
//         ).andReturn().getResponse();

//         assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//         assertThat(response.getContentAsString()).isEqualTo(
//             readSingleDtoJson.write(holdingReadDto1).getJson()
//         );
//     }

//     @Test
//     @SneakyThrows
//     void testUpdate() {
//         final UUID id = UUID.randomUUID();
//         final HoldingDto.Update holdingUpdateDto = new HoldingDto.Update("ticker1", 
//                                                                          "name1", 
//                                                                          BigDecimal.valueOf(50), 
//                                                                          BigDecimal.valueOf(100.4),
//                                                                          "sector1");

//         final Holding holding1 = mock(Holding.class);
//         final Holding.HoldingBuilder holding1Builder = mock(Holding.HoldingBuilder.class);
//         when(holding1.toBuilder()).thenReturn(holding1Builder);

//         when(repository.findById(id)).thenReturn(Optional.of(holding1));

//         final Holding updatedHolding1 = mock(Holding.class);
//         when(mapper.fromDto(holdingUpdateDto, holding1Builder)).thenReturn(updatedHolding1);
        
//         final Holding savedHolding1 = mock(Holding.class);
//         when(repository.save(updatedHolding1)).thenReturn(savedHolding1);

//         final HoldingDto.Read holdingReadDto1 = new HoldingDto.Read(UUID.randomUUID(), 
//                                                                     "ticker1", 
//                                                                     "name1", 
//                                                                     BigDecimal.valueOf(50), 
//                                                                     BigDecimal.valueOf(100.4), 
//                                                                     "sector1", 
//                                                                     "createdTime1", 
//                                                                     "updatedTime1");

//         when(mapper.toDto(savedHolding1)).thenReturn(holdingReadDto1);

//         final MockHttpServletResponse response = mvc.perform(
//             put("/holdings/" + id.toString()).contentType(MediaType.APPLICATION_JSON).content(
//                 updateDtoJson.write(holdingUpdateDto).getJson()
//             )
//         ).andReturn().getResponse();

//         assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//         assertThat(response.getContentAsString()).isEqualTo(
//             readSingleDtoJson.write(holdingReadDto1).getJson()
//         );
//     }

//     @Test
//     @SneakyThrows
//     void testDelete() {
//         final UUID id = UUID.randomUUID();

//         final MockHttpServletResponse response = mvc.perform(
//             delete("/holdings/" + id.toString())
//         ).andReturn().getResponse();

//         assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//         verify(repository).deleteById(id);
//     }
// }
