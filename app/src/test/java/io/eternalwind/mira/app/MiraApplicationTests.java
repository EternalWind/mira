package io.eternalwind.mira.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import io.eternalwind.mira.core.repositories.HoldingRepository;
import io.eternalwind.mira.core.repositories.InvestmentPlanRepository;

@SpringBootTest
class MiraApplicationTests {

    @MockBean
    private HoldingRepository holdingRepository;

    @MockBean
    private InvestmentPlanRepository investmentPlanRepository;

    @Test
    void contextLoads() {
    }

}
