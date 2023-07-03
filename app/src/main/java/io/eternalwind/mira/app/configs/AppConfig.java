package io.eternalwind.mira.app.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.eternalwind.mira.persistence.config.PersistenceConfig;

@Configuration
@ComponentScan(basePackageClasses = {
    PersistenceConfig.class
})
public class AppConfig {
    
}
