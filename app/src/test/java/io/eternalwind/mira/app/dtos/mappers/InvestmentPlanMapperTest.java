// package io.eternalwind.mira.app.dtos.mappers;

// import java.util.List;
// import java.util.Map;

// import org.assertj.core.api.WithAssertions;
// import org.junit.jupiter.api.Test;

// import io.eternalwind.mira.core.models.InvestmentPlan;

// public class InvestmentPlanMapperTest implements WithAssertions {
//     @Test
//     void fromSectorWeightsDtoShouldReturnNullForEmptyDto() {
//         final Map<String, Double> dto = Map.of();

//         assertThat(InvestmentPlanMapper.INSTANCE.fromSectorWeightsDto(dto)).isNull();
//     }

//     @Test
//     void fromSectorWeightsDtoShouldReturnWithoutOthers() {
//         final Map<String, Double> dto = Map.of(
//             "sector1", 0.1,
//             "Others", 0.9
//         );

//         final List<InvestmentPlan.SectorWeight> actual = InvestmentPlanMapper.INSTANCE.fromSectorWeightsDto(dto);
        
//         assertThat(actual).containsExactlyInAnyOrder(new InvestmentPlan.SectorWeight("sector1", 0.1));
//     }

//     @Test
//     void toSectorWeightsDtoShouldReturnEmptyForNullEntity() {
//         final List<InvestmentPlan.SectorWeight> entity = null;

//         assertThat(InvestmentPlanMapper.INSTANCE.toSectorWeightsDto(entity)).isEmpty();
//     }

//     @Test
//     void toSectorWeightsDtoShouldReturnDtoWithOthersAdded() {
//         final List<InvestmentPlan.SectorWeight> entity = List.of(
//             new InvestmentPlan.SectorWeight("sector1", 0.4)
//         );

//         final Map<String, Double> dto = InvestmentPlanMapper.INSTANCE.toSectorWeightsDto(entity);

//         assertThat(dto).containsOnly(entry("sector1", 0.4), entry("Others", 0.6));
//     }
// }
