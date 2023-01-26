package io.eternalwind.mira.app.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.eternalwind.mira.core.configs.CoreConfigs;

@Configuration
@ComponentScan(basePackageClasses = {
    CoreConfigs.class
})
public class AppConfig {
    
}
