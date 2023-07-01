package io.eternalwind.mira.core.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.eternalwind.mira.core.repositories.HoldingRepository;
import io.eternalwind.mira.core.repositories.InvestmentPlanRepository;

@Configuration
public class CoreConfigs {
    @Bean
    public HoldingRepository holdingRepository() {
        return new HoldingRepository();
    }

    @Bean
    public InvestmentPlanRepository investmentPlanRepository() {
        return new InvestmentPlanRepository();
    }
}
