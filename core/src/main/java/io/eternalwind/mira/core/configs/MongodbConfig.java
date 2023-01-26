package io.eternalwind.mira.core.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import io.eternalwind.mira.core.models.Entity;
import io.eternalwind.mira.core.repositories.Repositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackageClasses = Repositories.class)
public class MongodbConfig {
    @Bean
    public BeforeConvertCallback<Entity> entityBeforeConvertCallback() {
        return new Entity.BeforeSaveCallback();
    }
}
